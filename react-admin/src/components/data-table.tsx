import { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {
  Drawer,
  DrawerContent,
  DrawerDescription,
  DrawerHeader,
  DrawerTitle,
} from "@/components/ui/drawer";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Label } from "@/components/ui/label";
import { toast } from "sonner";
import { Plus, Pencil, Trash2, Loader2 } from "lucide-react";

export interface Column<T> {
  key: string;
  title: string;
  render?: (item: T) => React.ReactNode;
  width?: string;
}

export interface FormField {
  name: string;
  label: string;
  type: "text" | "textarea" | "select" | "number" | "email" | "password";
  placeholder?: string;
  options?: { label: string; value: string }[];
  required?: boolean;
}

interface DataDrawerProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  title: string;
  fields: FormField[];
  defaultValues: Record<string, unknown>;
  onSubmit: (data: Record<string, unknown>) => Promise<void>;
  isEdit?: boolean;
}

export function DataDrawer({
  open,
  onOpenChange,
  title,
  fields,
  defaultValues,
  onSubmit,
  isEdit,
}: DataDrawerProps) {
  const [formData, setFormData] = useState<Record<string, unknown>>(defaultValues);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setFormData(defaultValues);
  }, [defaultValues, open]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      await onSubmit(formData);
      onOpenChange(false);
      toast.success(isEdit ? "更新成功" : "创建成功");
    } catch (error) {
      toast.error(isEdit ? "更新失败" : "创建失败");
    } finally {
      setIsLoading(false);
    }
  };

  const handleChange = (name: string, value: unknown) => {
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <Drawer open={open} onOpenChange={onOpenChange} direction="right">
      <DrawerContent className="max-w-md">
        <DrawerHeader>
          <DrawerTitle>{title}</DrawerTitle>
          <DrawerDescription>
            {isEdit ? "修改" : "创建"}数据
          </DrawerDescription>
        </DrawerHeader>
        <form onSubmit={handleSubmit} className="p-4 space-y-4">
          {fields.map((field) => (
            <div key={field.name} className="space-y-2">
              <Label htmlFor={field.name}>
                {field.label}
                {field.required && <span className="text-red-500 ml-1">*</span>}
              </Label>
              {field.type === "textarea" ? (
                <textarea
                  id={field.name}
                  className="flex min-h-[80px] w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  placeholder={field.placeholder}
                  value={(formData[field.name] as string) || ""}
                  onChange={(e) => handleChange(field.name, e.target.value)}
                />
              ) : field.type === "select" ? (
                <Select
                  value={(formData[field.name] as string) || ""}
                  onValueChange={(value) => handleChange(field.name, value)}
                >
                  <SelectTrigger>
                    <SelectValue placeholder={field.placeholder} />
                  </SelectTrigger>
                  <SelectContent>
                    {field.options?.map((option) => (
                      <SelectItem key={option.value} value={option.value}>
                        {option.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              ) : (
                <Input
                  id={field.name}
                  type={field.type}
                  placeholder={field.placeholder}
                  value={(formData[field.name] as string) || ""}
                  onChange={(e) =>
                    handleChange(
                      field.name,
                      field.type === "number" ? Number(e.target.value) : e.target.value
                    )
                  }
                />
              )}
            </div>
          ))}
          <div className="flex justify-end gap-3 pt-4">
            <Button type="button" variant="outline" onClick={() => onOpenChange(false)}>
              取消
            </Button>
            <Button type="submit" disabled={isLoading}>
              {isLoading && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
              {isEdit ? "更新" : "创建"}
            </Button>
          </div>
        </form>
      </DrawerContent>
    </Drawer>
  );
}

interface DataTableProps<T> {
  columns: Column<T>[];
  data: T[];
  loading: boolean;
  searchPlaceholder?: string;
  onSearch?: (value: string) => void;
  searchValue?: string;
  onAdd?: () => void;
  onEdit?: (item: T) => void;
  onDelete?: (item: T) => void;
  onDeleteMultiple?: (items: T[]) => void;
  selectedKeys?: string[];
  onSelectionChange?: (keys: string[]) => void;
  rowKey: keyof T;
  addButtonText?: string;
}

export function DataTable<T>({
  columns,
  data,
  loading,
  searchPlaceholder = "搜索...",
  onSearch,
  searchValue,
  onAdd,
  onEdit,
  onDelete,
  onDeleteMultiple,
  selectedKeys,
  onSelectionChange,
  rowKey,
  addButtonText = "新增",
}: DataTableProps<T>) {
  const [selectAll, setSelectAll] = useState(false);

  const handleSelectAll = () => {
    if (selectAll) {
      onSelectionChange?.([]);
    } else {
      onSelectionChange?.(data.map((item) => String(item[rowKey])));
    }
    setSelectAll(!selectAll);
  };

  const handleSelectRow = (key: string) => {
    const newKeys = selectedKeys?.includes(key)
      ? selectedKeys.filter((k) => k !== key)
      : [...(selectedKeys || []), key];
    onSelectionChange?.(newKeys);
    setSelectAll(newKeys.length === data.length);
  };

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between gap-4">
        <div className="flex items-center gap-2 flex-1 max-w-sm">
          <Input
            placeholder={searchPlaceholder}
            value={searchValue}
            onChange={(e) => onSearch?.(e.target.value)}
            className="max-w-xs"
          />
        </div>
        <div className="flex items-center gap-2">
          {selectedKeys && selectedKeys.length > 0 && onDeleteMultiple && (
            <Button
              variant="destructive"
              size="sm"
              onClick={() => onDeleteMultiple(data.filter((item) => selectedKeys.includes(String(item[rowKey]))))}
            >
              <Trash2 className="h-4 w-4 mr-2" />
              删除 ({selectedKeys.length})
            </Button>
          )}
          {onAdd && (
            <Button onClick={onAdd}>
              <Plus className="h-4 w-4 mr-2" />
              {addButtonText}
            </Button>
          )}
        </div>
      </div>

      <div className="rounded-md border">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-12">
                <Checkbox
                  checked={selectAll}
                  onCheckedChange={handleSelectAll}
                />
              </TableHead>
              {columns.map((col) => (
                <TableHead key={col.key} style={{ width: col.width }}>
                  {col.title}
                </TableHead>
              ))}
              {(onEdit || onDelete) && <TableHead className="w-32">操作</TableHead>}
            </TableRow>
          </TableHeader>
          <TableBody>
            {loading ? (
              <TableRow>
                <TableCell colSpan={columns.length + 1} className="h-24 text-center">
                  <Loader2 className="h-6 w-6 animate-spin mx-auto" />
                </TableCell>
              </TableRow>
            ) : data.length === 0 ? (
              <TableRow>
                <TableCell colSpan={columns.length + 1} className="h-24 text-center text-muted-foreground">
                  暂无数据
                </TableCell>
              </TableRow>
            ) : (
              data.map((item) => (
                <TableRow key={String(item[rowKey])}>
                  <TableCell>
                    <Checkbox
                      checked={selectedKeys?.includes(String(item[rowKey]))}
                      onCheckedChange={() => handleSelectRow(String(item[rowKey]))}
                    />
                  </TableCell>
                  {columns.map((col) => (
                    <TableCell key={col.key}>
                      {col.render
                        ? col.render(item)
                        : String((item as Record<string, unknown>)[col.key])}
                    </TableCell>
                  ))}
                  {(onEdit || onDelete) && (
                    <TableCell>
                      <div className="flex items-center gap-2">
                        {onEdit && (
                          <Button
                            variant="ghost"
                            size="icon"
                            onClick={() => onEdit(item)}
                          >
                            <Pencil className="h-4 w-4" />
                          </Button>
                        )}
                        {onDelete && (
                          <Button
                            variant="ghost"
                            size="icon"
                            onClick={() => onDelete(item)}
                          >
                            <Trash2 className="h-4 w-4" />
                          </Button>
                        )}
                      </div>
                    </TableCell>
                  )}
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}
