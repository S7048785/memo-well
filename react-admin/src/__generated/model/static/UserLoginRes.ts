/**
 * Entity for table "users"
 */
export interface UserLoginRes {
    readonly id: number;
    readonly email: string;
    /**
     * 用户名
     */
    readonly username: string;
    /**
     * 头像URL
     */
    readonly avatar?: string | undefined;
    readonly bio?: string | undefined;
    readonly gender?: number | undefined;
}
