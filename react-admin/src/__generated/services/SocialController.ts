import type {Executor} from '../';
import type {CommentsCreateInput, R} from '../model/static/';

export class SocialController {
    
    constructor(private executor: Executor) {}
    
    readonly getCommentList: (options: SocialControllerOptions['getCommentList']) => Promise<
        void
    > = async(options) => {
        let _uri = '/social/comment/';
        _uri += encodeURIComponent(options.postId);
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.page;
        _uri += _separator
        _uri += 'page='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        _value = options.limit;
        _uri += _separator
        _uri += 'limit='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<void>;
    }
    
    readonly like: (options: SocialControllerOptions['like']) => Promise<
        R<boolean>
    > = async(options) => {
        let _uri = '/social/like';
        let _separator = _uri.indexOf('?') === -1 ? '?' : '&';
        let _value: any = undefined;
        _value = options.postId;
        _uri += _separator
        _uri += 'postId='
        _uri += encodeURIComponent(_value);
        _separator = '&';
        return (await this.executor({uri: _uri, method: 'POST'})) as Promise<R<boolean>>;
    }
    
    readonly postComment: (options: SocialControllerOptions['postComment']) => Promise<
        R<string | undefined>
    > = async(options) => {
        let _uri = '/social/comment';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<R<string | undefined>>;
    }
}

export type SocialControllerOptions = {
    'like': {
        readonly postId: number
    }, 
    'postComment': {
        readonly body: CommentsCreateInput
    }, 
    'getCommentList': {
        readonly postId: number, 
        readonly page: number, 
        readonly limit: number
    }
}
