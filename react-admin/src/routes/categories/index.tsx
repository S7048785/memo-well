import { createFileRoute } from "@tanstack/react-router"
import { DataTable, type Column } from "@/components/data-table"
import { useState } from "react"

type CategoryItem = {
  id: number
  name: string
  createdAt: string
}

export const Route = createFileRoute("/categories/")({
  component: CategoryPage,
})


function CategoryPage() {
  const [searchValue, setSearchValue] = useState("")
  const [selectedKeys, setSelectedKeys] = useState<string[]>([])

  const columns: Column<CategoryItem>[] = [
    {
      key: "id",
      title: "ID",
      width: "60px",
    },
    {
      key: "name",
      title: "分类名称",
    },
    {
      key: "createdAt",
      title: "创建时间",
      render: (item) => item.createdAt?.slice(0, 10) || "-",
    },
  ]

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold">分类管理</h1>
      </div>
      <DataTable
        columns={columns}
        data={[]}
        loading={false}
        rowKey="id"
        searchPlaceholder="搜索分类..."
        searchValue={searchValue}
        onSearch={setSearchValue}
        selectedKeys={selectedKeys}
        onSelectionChange={setSelectedKeys}
      />
    </div>
  )
}
