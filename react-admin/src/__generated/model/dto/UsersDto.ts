export type UsersDto = {
    'UserController/USER_LOGIN': {
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
    }
}
