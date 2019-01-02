export class DeviceListElement {
    id: number;
    name: string;
    serialNumber: string;
    deviceModel:string;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    username:string;
}

export class DeviceAddElement {
    name: string;
    serialNumber: string;
    deviceModel:string;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    username:string;
}

export class DeviceEditElement {
    id: number;
    name: string;
    serialNumber: string;
    deviceModel:string;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    username:string;
}

export class DeviceViewElement {
    id: number;
    name: string;
    serialNumber: string;
    deviceModel:string;
    deviceTypeName:string;
    manufacture:string;
    lastUpdate:string
    location:string;
    username:string;
}

export class DeviceModelViewElement {
    id: number;
    name: string;
    manufacture:string;
    owner:string;
    numberOfDevices;
    type:string;

}
export class DeviceModelAddElement {
    name: string;
    manufacture:string;
    type:string;

}

export class DeviceModelEditElement {
    id: number;
    name: string;
    manufacture:string;
    type:string;

}

export class DeviceModelListElement {
    id: number;
    name: string;
    manufacture:string;
    type:string;

}

export class DeviceTypeListElement {
    id: number;
    name: string;
}
export class ParameterListElement{
    id: number;
    name: string;
    value:string;
}