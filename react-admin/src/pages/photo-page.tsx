import { useQuery } from "@tanstack/react-query"
import { PostController } from "@/__generated/services/PostController"
import { DataTable, type Column } from "@/components/data-table"
import { useState } from "react"
import { api } from "@/ApiInstance"

type PhotoItem = {
  id: number
  imageUrl: string
  category: { id: number; name: string } | undefined
  user: { id: number; username: string }
  likeCount: number
  commentCount: number
  createdAt: string
}


export function PhotoPage() {
  const [searchValue, setSearchValue] = useState("")
  const [selectedKeys, setSelectedKeys] = useState<string[]>([])

  const { data, isLoading, refetch } = useQuery({
    queryKey: ["photos", searchValue],
    queryFn: async () => {
      const res = await api.postController.getNoteList({
        limit: 100,
        size: 1,
        categoryId: 2,
      })
      return res.list || []
    },
  })

  const columns: Column<PhotoItem>[] = [
    {
      key: "id",
      title: "ID",
      width: "60px",
    },
    {
      key: "imageUrl",
      title: "图片",
      render: (item) => (
        item.imageUrl ? (
          <img src={item.imageUrl} alt="" className="w-16 h-16 object-cover rounded" />
        ) : "-"
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

  const handleDelete = async (item: PhotoItem) => {
    if (!confirm(`确定删除 ID 为 ${item.id} 的图片吗？`)) return
    try {
      await api.postController.delete({ postId: item.id })
      refetch()
    } catch (error) {
      console.error(error)
    }
  }

  const handleDeleteMultiple = async (items: PhotoItem[]) => {
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
        <h1 className="text-2xl font-bold">图片管理</h1>
      </div>
      <DataTable
        columns={columns}
        data={data as PhotoItem[]}
        loading={isLoading}
        rowKey="id"
        searchPlaceholder="搜索..."
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
