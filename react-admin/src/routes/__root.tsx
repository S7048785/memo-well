import { createRootRoute, Outlet, redirect } from "@tanstack/react-router"
import { useAuth } from "@/lib/auth"
import { Layout } from "@/components/layout"
import { Toaster } from "@/components/ui/sonner"

function AdminLayout() {
  // const { user, isLoading } = useAuth()

  // if (isLoading) {
  //   return (
  //     <div className="min-h-screen flex items-center justify-center">
  //       <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary" />
  //     </div>
  //   )
  // }

  // if (!user) {
    // throw redirect({ to: "/login" })
  // }

  return (
    <>
      <Layout>
        <Outlet />
      </Layout>
      <Toaster />
    </>
  )
}

export const Route = createRootRoute({
  component: AdminLayout,
})
