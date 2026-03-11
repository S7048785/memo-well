import type {CommentsCreateInput_TargetOf_parent, CommentsCreateInput_TargetOf_post} from './';

/**
 * Entity for table "comments"
 */
export interface CommentsCreateInput {
    /**
     * 所属帖子ID
     */
    readonly post: CommentsCreateInput_TargetOf_post;
    /**
     * 父评论ID（用于回复功能）
     */
    readonly parent?: CommentsCreateInput_TargetOf_parent | undefined;
    /**
     * 评论内容
     */
    readonly content: string;
}
