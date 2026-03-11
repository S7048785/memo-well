export type PostsDto = {
    'PostController/POST_ITEM': {
        readonly id: number;
        /**
         * 分类ID
         */
        readonly category?: {
            readonly id: number;
            /**
             * 分类名称，如：表白、闲置、树洞
             */
            readonly name: string;
        } | undefined;
        /**
         * 文字内容
         */
        readonly content?: string | undefined;
        /**
         * 图片存储路径
         */
        readonly imageUrl?: string | undefined;
        /**
         * 卡片背景颜色
         */
        readonly cardColor?: string | undefined;
        /**
         * 发布者ID
         */
        readonly user: {
            readonly id: number;
            /**
             * 用户名
             */
            readonly username: string;
        };
        readonly likeCount?: number | undefined;
        /**
         * 评论量
         */
        readonly commentCount?: number | undefined;
        readonly createdAt?: string | undefined;
    }
}
