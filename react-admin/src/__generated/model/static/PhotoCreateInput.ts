/**
 * Entity for table "posts"
 */
export interface PhotoCreateInput {
    /**
     * 图片存储路径
     */
    readonly imageUrl?: string | undefined;
    /**
     * 文字内容
     */
    readonly content?: string | undefined;
}
