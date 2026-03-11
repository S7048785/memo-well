import { createFileRoute } from "@tanstack/react-router"
import { useQuery } from "@tanstack/react-query"
import { DataTable, type Column } from "@/components/data-table"
import { useState } from "react"
import { api } from "@/ApiInstance"

type NoteItem = {
  id: number
  content: string
  category: { id: number; name: string } | undefined
  user: { id: number; username: string }
  likeCount: number
  commentCount: number
  createdAt: string
}

export const Route = createFileRoute("/notes/")({
  component: NotePage,
})


function NotePage() {
  const [searchValue, setSearchValue] = useState("")
  const [selectedKeys, setSelectedKeys] = useState<string[]>([])

  const { data, isLoading, refetch } = useQuery({
    queryKey: ["notes", searchValue],
    queryFn: async () => {
      const res = await api.postController.getNoteList({
        limit: 100,
        size: 1,
        categoryId: 1,
      })
      return res.list || []
    },
  })

  const columns: Column<NoteItem>[] = [
    {
      key: "id",
      title: "ID",
      width: "60px",
    },
    {
      key: "content",
      title: "内容",
      render: (item) => (
        <span className="line-clamp-2 max-w-xs">{item.content}</span>
      ),
    },
    {
      key: "category",
      title: "分类",
      render: (item) => item.category?.name || "-",
    },
    {
      key: "username",
      title: "发布者",
      render: (item) => item.user.username,
    },
    {
      key: "likeCount",
      title: "点赞数",
      width: "80px",
    },
    {
      key: "commentCount",
      title: "评论数",
      width: "80px",
    },
    {
      key: "createdAt",
      title: "发布时间",
      render: (item) => item.createdAt?.slice(0, 10) || "-",
    },
  ]

  const handleDelete = async (item: NoteItem) => {
    if (!confirm(`确定删除 ID 为 ${item.id} 的便签吗？`)) return
    try {
      await api.postController.delete({ postId: item.id })
      refetch()
    } catch (error) {
      console.error(error)
    }
  }

  const handleDeleteMultiple = async (items: NoteItem[]) => {
    try {
      await Promise.all(items.map((item) => api.postController.delete({ postId: item.id })))
      refetch()
      setSelectedKeys([])
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold">便签管理</h1>
      </div>
      <DataTable
        columns={columns}
        data={data as NoteItem[]}
        loading={isLoading}
        rowKey="id"
        searchPlaceholder="搜索便签内容..."
        searchValue={searchValue}
        onSearch={setSearchValue}
        onDelete={handleDelete}
        onDeleteMultiple={handleDeleteMultiple}
        selectedKeys={selectedKeys}
        onSelectionChange={setSelectedKeys}
      />
    </div>
  )
}
