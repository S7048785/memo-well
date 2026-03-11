import type {Executor} from '../';
import type {CategoriesDto} from '../model/dto/';
import type {R} from '../model/static/';

export class CommonController {
    
    constructor(private executor: Executor) {}
    
    readonly categoryList: () => Promise<
        R<ReadonlyArray<CategoriesDto['CommonController/CATEGORY_ITEM']>>
    > = async() => {
        let _uri = '/common/categories';
        return (await this.executor({uri: _uri, method: 'GET'})) as Promise<R<ReadonlyArray<CategoriesDto['CommonController/CATEGORY_ITEM']>>>;
    }
}

export type CommonControllerOptions = {
    'categoryList': {}
}
