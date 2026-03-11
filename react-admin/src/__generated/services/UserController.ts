import type {Executor} from '../';
import type {UsersDto} from '../model/dto/';
import type {
    R, 
    UserLoginReq, 
    UserLoginRes, 
    UserRegisterReq
} from '../model/static/';

export class UserController {
    
    constructor(private executor: Executor) {}
    
    readonly get: () => Promise<
        R<UsersDto['UserController/USER_LOGIN']>
    > = async() => {
        let _uri = '/user/me';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<R<UsersDto['UserController/USER_LOGIN']>>;
    }
    
    readonly login: (options: UserControllerOptions['login']) => Promise<
        R<UserLoginRes>
    > = async(options) => {
        let _uri = '/user/login';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<R<UserLoginRes>>;
    }
    
    readonly register: (options: UserControllerOptions['register']) => Promise<
        R<string | undefined>
    > = async(options) => {
        let _uri = '/user/register';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<R<string | undefined>>;
    }
}

export type UserControllerOptions = {
    'login': {
        readonly body: UserLoginReq
    }, 
    'register': {
        readonly body: UserRegisterReq
    }, 
    'get': {}
}
