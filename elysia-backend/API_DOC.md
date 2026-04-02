# Memo-Well 接口文档

## 基础信息

- **基础URL**: `http://localhost:3000/api`
- **认证方式**: JWT Token (待确认)
- **Content-Type**: `application/json`

---

## 数据模型

### 用户 (users)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 用户ID |
| email | varchar(64) | 邮箱 |
| username | varchar(50) | 用户名 |
| password | varchar(255) | 密码(加密) |
| avatar | varchar(255) | 头像URL |
| bio | varchar(64) | 简介 |
| gender | tinyint | 0保密, 1男, 2女 |
| role | varchar(10) | 角色 |
| created_at | datetime | 创建时间 |

### 帖子 (posts)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 帖子ID |
| user_id | int | 发布者ID |
| category_id | int | 分类ID |
| type | tinyint | 1:便签(文字), 2:照片 |
| content | text | 文字内容 |
| image_url | varchar(255) | 图片路径 |
| card_color | varchar(20) | 卡片背景颜色 |
| like_count | int | 点赞数 |
| comment_count | int | 评论数 |
| is_top | tinyint | 是否置顶 |
| created_at | datetime | 创建时间 |

### 评论 (comments)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 评论ID |
| post_id | int | 所属帖子ID |
| user_id | int | 评论者ID |
| parent_id | int | 父评论ID(回复功能) |
| content | text | 评论内容 |
| created_at | datetime | 创建时间 |

### 点赞 (likes)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 点赞ID |
| user_id | int | 用户ID |
| post_id | int | 帖子ID |
| created_at | datetime | 点赞时间 |

### 分类 (categories)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int | 分类ID |
| name | varchar(50) | 分类名称 |
| sort_order | int | 排序权重 |

---

## 用户模块

### 登录
```
POST /api/auth/login
```

**请求参数**
```json
{
  "email": "string",
  "password": "string"
}
```

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "token": "jwt_token_here",
    "user": {
      "id": 1,
      "username": "张三",
      "email": "zhangsan@example.com",
      "avatar": "https://example.com/avatar.jpg",
      "bio": "这个人很懒",
      "gender": 1,
      "role": "user"
    }
  }
}
```

---

### 注册
```
POST /api/auth/register
```

**请求参数**
```json
{
  "email": "string",
  "username": "string",
  "password": "string"
}
```

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "张三",
    "email": "zhangsan@example.com"
  }
}
```

---

### 获取个人信息
```
GET /api/user/profile
```

**请求头**
```
Authorization: Bearer <token>
```

**响应示例**

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "张三",
    "email": "zhangsan@example.com",
    "avatar": "https://example.com/avatar.jpg",
    "bio": "这个人很懒",
    "gender": 1,
    "role": "user",
    "created_at": "2024-01-01 00:00:00"
  }
}
```

---

## 帖子模块

### 分页获取帖子列表
```
GET /api/posts
```

**查询参数**
| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| page | int | 否 | 页码，默认1 |
| pageSize | int | 否 | 每页数量，默认10 |
| type | int | 是   | 1:便签, 2:图片 |
| category_id | int | 否 | 分类ID |

**响应示例**

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "user_id": 1,
        "username": "张三",
        "avatar": "https://example.com/avatar.jpg",
        "category_id": 1,
        "category_name": "表白",
        "type": 1,
        "content": "这是一条便签内容",
        "image_url": null,
        "card_color": "#FFFFFF",
        "like_count": 10,
        "comment_count": 5,
        "is_top": 0,
        "created_at": "2024-01-01 00:00:00",
        "is_liked": true
      },
      {
        "id": 2,
        "user_id": 2,
        "username": "李四",
        "avatar": "https://example.com/avatar2.jpg",
        "category_id": 2,
        "category_name": "闲置",
        "type": 2,
        "content": null,
        "image_url": "https://example.com/image.jpg",
        "card_color": "#FFE4E1",
        "like_count": 20,
        "comment_count": 3,
        "is_top": 1,
        "created_at": "2024-01-02 00:00:00",
        "is_liked": false
      }
    ],
    "pagination": {
      "page": 1,
      "pageSize": 10,
      "total": 100,
      "totalPages": 10
    }
  }
}
```

---

### 获取帖子详情
```
GET /api/posts/:id
```

**路径参数**
| 参数 | 类型 | 说明 |
|------|------|------|
| id | int | 帖子ID |

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "id": 1,
    "user_id": 1,
    "username": "张三",
    "avatar": "https://example.com/avatar.jpg",
    "category_id": 1,
    "category_name": "表白",
    "type": 1,
    "content": "这是一条便签内容",
    "image_url": null,
    "card_color": "#FFFFFF",
    "like_count": 10,
    "comment_count": 5,
    "is_top": 0,
    "created_at": "2024-01-01 00:00:00",
    "is_liked": true
  }
}
```

---

### 发布便签
```
POST /api/posts/note
```

**请求头**
```
Authorization: Bearer <token>
```

**请求参数**
```json
{
  "content": "string",
  "category_id": 1,
  "card_color": "#FFFFFF"
}
```

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "id": 1,
    "type": 1,
    "content": "便签内容",
    "card_color": "#FFFFFF"
  }
}
```

---

### 发布图片
```
POST /api/posts/image
```

**请求头**
```
Authorization: Bearer <token>
Content-Type: multipart/form-data
```

**请求参数**
| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| image | file | 是 | 图片文件 |
| content | string | 否 | 图片描述 |
| category_id | int | 否 | 分类ID |
| card_color | string | 否 | 卡片背景颜色 |

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "id": 1,
    "type": 2,
    "image_url": "/uploads/xxx.jpg",
    "content": "图片描述",
    "card_color": "#FFE4E1"
  }
}
```

---

### 删除自己的帖子
```
DELETE /api/posts/:id
```

**请求头**
```
Authorization: Bearer <token>
```

**路径参数**
| 参数 | 类型 | 说明 |
|------|------|------|
| id | int | 帖子ID |

**响应示例**
```json
{
  "code": 200,
  "msg": "删除成功",
  "data": null
}
```

---

### 根据用户名搜索帖子
```
GET /api/posts/search
```

**查询参数**
| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| username | string | 是 | 用户名 |
| page | int | 否 | 页码，默认1 |
| pageSize | int | 否 | 每页数量，默认10 |

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "user_id": 1,
        "username": "张三",
        "type": 1,
        "content": "帖子内容",
        "like_count": 10,
        "comment_count": 5,
        "created_at": "2024-01-01 00:00:00"
      }
    ],
    "pagination": {
      "page": 1,
      "pageSize": 10,
      "total": 50,
      "totalPages": 5
    }
  }
}
```

---

## 通用模块

### 点赞
```
POST /api/likes
```

**请求头**
```
Authorization: Bearer <token>
```

**请求参数**
```json
{
  "post_id": 1
}
```

**响应示例**
```json
{
  "code": 200,
  "msg": "点赞成功",
  "data": {
    "id": 1,
    "post_id": 1,
    "created_at": "2024-01-01 00:00:00"
  }
}
```

**取消点赞**: 再次调用此接口或使用 `DELETE /api/likes/:post_id`

---

### 发布评论
```
POST /api/comments
```

**请求头**
```
Authorization: Bearer <token>
```

**请求参数**
```json
{
  "post_id": 1,
  "content": "评论内容",
  "parent_id": 0
}
```

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| post_id | int | 是 | 帖子ID |
| content | string | 是 | 评论内容 |
| parent_id | int | 否 | 父评论ID(用于回复，默认0) |

**响应示例**
```json
{
  "code": 200,
  "msg": "评论成功",
  "data": {
    "id": 1,
    "post_id": 1,
    "user_id": 1,
    "username": "张三",
    "avatar": "https://example.com/avatar.jpg",
    "content": "评论内容",
    "parent_id": 0,
    "created_at": "2024-01-01 00:00:00"
  }
}
```

---

### 获取评论列表
```
GET /api/comments/:post_id
```

**路径参数**
| 参数 | 类型 | 说明 |
|------|------|------|
| post_id | int | 帖子ID |

**查询参数**
| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| page | int | 否 | 页码，默认1 |
| pageSize | int | 否 | 每页数量，默认20 |

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "post_id": 1,
        "user_id": 1,
        "username": "张三",
        "avatar": "https://example.com/avatar.jpg",
        "content": "这是一条评论",
        "parent_id": 0,
        "created_at": "2024-01-01 00:00:00",
        "replies": [
          {
            "id": 2,
            "post_id": 1,
            "user_id": 2,
            "username": "李四",
            "avatar": "https://example.com/avatar2.jpg",
            "content": "这是回复",
            "parent_id": 1,
            "created_at": "2024-01-01 00:01:00"
          }
        ]
      }
    ],
    "pagination": {
      "page": 1,
      "pageSize": 20,
      "total": 50,
      "totalPages": 3
    }
  }
}
```

---

## 分类模块

### 获取分类列表
```
GET /api/categories
```

**响应示例**
```json
{
  "code": 200,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "表白",
      "sort_order": 1
    },
    {
      "id": 2,
      "name": "闲置",
      "sort_order": 2
    },
    {
      "id": 3,
      "name": "树洞",
      "sort_order": 3
    }
  ]
}
```

---

## 错误码

| 错误码 | 说明 |
|--------|------|
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权(未登录或token过期) |
| 403 | 禁止访问(无权限) |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |
