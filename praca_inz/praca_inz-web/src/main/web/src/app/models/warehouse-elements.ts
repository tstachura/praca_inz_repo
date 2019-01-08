export class WarehouseListElement {
    id: number;
    name: string;
    username: string;
    officeId:number
    officeName: string;
    devicesNumber:string;
    deleted:boolean;
}

export class WarehouseViewElement {
    id: number;
    name: string;
    description: string;
    city: string;
    street: string;
    buildingNumber: string;
    flatNumber: string;
    zipCode: string;
    parentName: string;
}


export class WarehouseAddElement {
    id: number;
    name: string;
    username: string;
    officeId:number
    officeName: string;
    devicesNumber:string;
}

export class DeliveryListElement{
    id:number;
    deliveryNumber:string;
    title:string;
    username:string;
    status:string;
    description:string
    createDate:string;
    lastUpdate:string;
    sender:string;
    receiver:string
}

export class ShipmentListElement{
    id:number;
    shipmentNumber:string;
    title:string;
    username:string;
    status:string;
    description:string
    createDate:string;
    lastUpdate:string;
    sender:string;
    receiver:string

}
