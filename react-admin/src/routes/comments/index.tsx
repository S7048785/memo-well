import { createFileRoute } from "@tanstack/react-router"
import { DataTable, type Column } from "@/components/data-table"
import { useState } from "react"
export const Route = createFileRoute("/comments/")({
  component: CommentPage,
})


type CommentItem = {
  id: number
  content: string
  username: string
  postId: number
  createdAt: string
}

function CommentPage() {
  const [searchValue, setSearchValue] = useState("")
  const [selectedKeys, setSelectedKeys] = useState<string[]>([])

  const columns: Column<CommentItem>[] = [
    {
      key: "id",
      title: "ID",
      width: "60px",
    },
    {
      key: "content",
      title: "评论内容",
      render: (item) => (
        <span className="line-clamp-2 max-w-xs">{item.content}</span>
      ),
    },
    {
      key: "username",
      title: "发布者",
    },
    {
      key: "postId",
      title: "所属帖子",
      width: "80px",
    },
    {
      key: "createdAt",
      title: "发布时间",
      render: (item) => item.createdAt?.slice(0, 10) || "-",
    },
  ]

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold">评论管理</h1>
      </div>
      <DataTable
        columns={columns}
        data={[]}
        loading={false}
        rowKey="id"
        searchPlaceholder="搜索评论..."
        searchValue={searchValue}
        onSearch={setSearchValue}
        selectedKeys={selectedKeys}
        onSelectionChange={setSelectedKeys}
      />
    </div>
  )
}
