export interface PageRes<T> {
    readonly total: number;
    readonly page: number;
    readonly page_size: number;
    readonly list: ReadonlyArray<T>;
}
