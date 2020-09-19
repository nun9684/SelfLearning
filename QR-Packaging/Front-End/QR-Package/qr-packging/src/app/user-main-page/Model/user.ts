import { PackageList } from './packageList';
export class User {
    constructor(
        public ID: string,
        public packageList: PackageList
    ) {}
}
