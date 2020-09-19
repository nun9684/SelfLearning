export class PackageList {
    constructor(
        public packageID: string,
        public credential: string,
        public receiverAddress: string,
        public senderAddress: string
    ){}
}
