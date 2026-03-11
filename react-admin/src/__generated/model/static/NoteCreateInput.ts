/**
 * Entity for table "posts"
 */
export interface NoteCreateInput {
    readonly categoryId?: number | undefined;
    /**
     * 文字内容
     */
    readonly content?: string | undefined;
    /**
     * 卡片背景颜色
     */
    readonly cardColor?: string | undefined;
}
