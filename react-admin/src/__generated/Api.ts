import type {Executor} from './';
import {
    AdminController, 
    CommonController, 
    PostController, 
    SocialController, 
    UserController
} from './services/';

export class Api {
    
    readonly adminController: AdminController
    
    readonly commonController: CommonController
    
    readonly postController: PostController
    
    readonly socialController: SocialController
    
    readonly userController: UserController
    
    constructor(executor: Executor) {
        this.adminController = new AdminController(executor);
        this.commonController = new CommonController(executor);
        this.postController = new PostController(executor);
        this.socialController = new SocialController(executor);
        this.userController = new UserController(executor);
    }
}