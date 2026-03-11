import type {Executor} from '../';
import type {PostsDto} from '../model/dto/';
import type {
    NoteCreateInput, 
    PageRes, 
    PhotoCreateInput, 
    R
} from '../model/static/';

export class PostController {
    
    constructor(private executor: Executor) {}
    
    readonly delete: (options: PostControllerOptions['delete']) => Promise<
        R<string | undefined>
    > = async(options) => {
        let _uri = '/posts/';
        _uri += encodeURIComponent(options.postId);
        return (await this.executor({uri: _uri, method: 'DELETE'})) as Promise<R<string | undefined>>;
    }
    
    readonly getDetail: (options: PostControllerOptions['getDetail']) => Promise<
        void
    > = async(options) => {
        let _uri = '/posts/';
        _uri += encodeURIComponent(options.id);
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<void>;
    }
    
    readonly getNoteList: (options: PostControllerOptions['getNoteList']) => Promise<
        PageRes<PostsDto['PostController/POST_ITEM']>
    > = async(options) => {
        let _uri = '/posts/';
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.limit;
        _uri += _separator
        _uri += 'limit='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        _value = options.size;
        _uri += _separator
        _uri += 'size='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        _value = options.categoryId;
        _uri += _separator
        _uri += 'categoryId='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<PageRes<PostsDto['PostController/POST_ITEM']>>;
    }
    
    readonly postNote: (options: PostControllerOptions['postNote']) => Promise<
        R<string | undefined>
    > = async(options) => {
        let _uri = '/posts/';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<R<string | undefined>>;
    }
    
    readonly postPhoto: (options: PostControllerOptions['postPhoto']) => Promise<
        R<string | undefined>
    > = async(options) => {
        let _uri = '/posts/upload';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<R<string | undefined>>;
    }
    
    readonly search: (options: PostControllerOptions['search']) => Promise<
        PageRes<PostsDto['PostController/POST_ITEM']>
    > = async(options) => {
        let _uri = '/posts/search';
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.username;
        _uri += _separator
        _uri += 'username='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        _value = options.limit;
        _uri += _separator
        _uri += 'limit='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        _value = options.size;
        _uri += _separator
        _uri += 'size='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<PageRes<PostsDto['PostController/POST_ITEM']>>;
    }
}

export type PostControllerOptions = {
    'getNoteList': {
        readonly limit: number, 
        readonly size: number, 
        readonly categoryId: number
    }, 
    'getDetail': {
        readonly id: number
    }, 
    'postNote': {
        readonly body: NoteCreateInput
    }, 
    'postPhoto': {
        readonly body: PhotoCreateInput
    }, 
    'delete': {
        readonly postId: number
    }, 
    'search': {
        readonly username: string, 
        readonly limit: number, 
        readonly size: number
    }
}
