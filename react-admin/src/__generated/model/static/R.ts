export interface R<T> {
    readonly code?: number | undefined;
    readonly msg?: string | undefined;
    readonly data?: T | undefined;
}
