import type {Executor} from '../';
import type {R, UserLoginRes} from '../model/static/';

export class AdminController {
    
    constructor(private executor: Executor) {}
    
    readonly getInfo: () => Promise<
        R<UserLoginRes | undefined>
    > = async() => {
        let _uri = '/admin/me';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<R<UserLoginRes | undefined>>;
    }
    
    readonly login: (options: AdminControllerOptions['login']) => Promise<
        R<UserLoginRes>
    > = async(options) => {
        let _uri = '/admin/login';
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.username;
        _uri += _separator
        _uri += 'username='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        _value = options.password;
        _uri += _separator
        _uri += 'password='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        return (await this.executor({uri: _uri, method: 'POST'})) as Promise<R<UserLoginRes>>;
    }
    
    readonly logout: () => Promise<
        R<string | undefined>
    > = async() => {
        let _uri = '/admin/logout';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<R<string | undefined>>;
    }
}

export type AdminControllerOptions = {
    'login': {
        readonly username: string, 
        readonly password: string
    }, 
    'getInfo': {}, 
    'logout': {}
}
