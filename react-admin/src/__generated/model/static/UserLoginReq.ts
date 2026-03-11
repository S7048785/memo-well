/**
 * Entity for table "users"
 */
export interface UserLoginReq {
    readonly email: string;
    /**
     * 加密后的密码
     */
    readonly password: string;
}
