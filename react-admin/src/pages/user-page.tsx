import { DataTable, type Column } from "@/components/data-table"
import { useState } from "react"

type UserItem = {
  id: number
  username: string
  email: string
  avatar: string
  createdAt: string
}

export function UserPage() {
  const [searchValue, setSearchValue] = useState("")
  const [selectedKeys, setSelectedKeys] = useState<string[]>([])

  const columns: Column<UserItem>[] = [
    {
      key: "id",
      title: "ID",
      width: "60px",
    },
    {
      key: "avatar",
      title: "头像",
      render: (item) => (
        item.avatar ? (
          <img src={item.avatar} alt="" className="w-10 h-10 rounded-full object-cover" />
        ) : "-"
      ),
    },
    {
      key: "username",
      title: "用户名",
    },
    {
      key: "email",
      title: "邮箱",
    },
    {
      key: "createdAt",
      title: "注册时间",
      render: (item) => item.createdAt?.slice(0, 10) || "-",
    },
  ]

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold">用户管理</h1>
      </div>
      <DataTable
        columns={columns}
        data={[]}
        loading={false}
        rowKey="id"
        searchPlaceholder="搜索用户..."
        searchValue={searchValue}
        onSearch={setSearchValue}
        selectedKeys={selectedKeys}
        onSelectionChange={setSelectedKeys}
      />
    </div>
  )
}
