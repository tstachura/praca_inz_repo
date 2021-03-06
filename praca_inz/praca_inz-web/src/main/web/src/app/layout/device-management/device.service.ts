import {Injectable} from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {TransferListElement} from "../../models/transfer-list-element";
import {StructureAddElement, StructureEditElement, StructureViewElement} from "../../models/structure-elements";
import {
    DeviceAddElement,
    DeviceEditElement,
    DeviceListElement, DeviceModelAddElement, DeviceModelEditElement,
    DeviceModelListElement, DeviceModelViewElement,
    DeviceTypeListElement, DeviceViewElement,
    ParameterListElement
} from "../../models/device-elements";
import {TransferAddElement} from "../../models/transfer-add-element";


@Injectable({
    providedIn: 'root'
})
export class DeviceService {

    private devicePath = this.configuration.ServerWithApiUrl + '/device';
    private transferPath = this.configuration.ServerWithApiUrl + '/transfer';

    constructor(private httpService: HttpService, private configuration: Configuration) {
    }

    //Devices

    getDevice(id: string) {
        return this.httpService.get<DeviceViewElement>(this.devicePath + '/' + id);
    }

    getDeviceEdit(id: string) {
        return this.httpService.get<DeviceEditElement>(this.devicePath + '/edit/' + id);
    }

    getAllDevices(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath);
    }

    getAllDevicesForLoggedUser(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/user");
    }

    getAllDevicesForRequest(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/request");
    }

    getAllDevicesForWarehouseman(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/warehouseman");
    }

    getAllDevicesForUser(username: String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/user/" + username);
    }

    getAllDevicesForCompany(id: number): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/company/" + id);
    }

    getAllDevicesForDepartment(id: String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/department/" + id);
    }

    getAllTransfersForLoggedUser(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath + "/user");
    }

    updateDevice(data:DeviceEditElement){
        return this.httpService.put<DeviceEditElement>(this.devicePath, data);
    }

    deleteDevice(id :string): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/'+id);
    }

    createDevice(data:DeviceAddElement): Observable<any>{
        return this.httpService.post<DeviceAddElement>(this.devicePath,data);
    }

    //Device Models

    getDeviceModelView(id: string) {
        return this.httpService.get<DeviceModelViewElement>(this.devicePath + '/model/' + id);
    }

    getDeviceModelEdit(id: string) {
        return this.httpService.get<DeviceModelEditElement>(this.devicePath + '/model/edit/' + id);
    }

    getAllDevicesModels(): Observable<DeviceModelListElement[]> {
        return this.httpService.get<DeviceModelListElement[]>(this.devicePath + "/model");
    }

    getAllDevicesModelsForCompany(id: number): Observable<DeviceModelListElement[]> {
        return this.httpService.get<DeviceModelListElement[]>(this.devicePath + "/model/company/" + id);
    }

    deleteDeviceModel(id :string): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/model/'+id);
    }

    updateDeviceModel(data:DeviceModelEditElement){
        return this.httpService.put<DeviceModelEditElement>(this.devicePath+"/model/", data);
    }

    createDeviceModel(data:DeviceModelAddElement): Observable<any>{
        return this.httpService.post<DeviceModelAddElement>(this.devicePath+"/model/",data);
    }

    //Parameters

    getAllParametersForDeviceModel(id: number): Observable<ParameterListElement[]> {
        return this.httpService.get<ParameterListElement[]>(this.devicePath + "/model/parameters/"+id);
    }

    getAllParametersForDevice(id: string):Observable<ParameterListElement[]>  {
        return this.httpService.get<ParameterListElement[]>(this.devicePath + "/parameters/"+id);
    }

    createParameter(data:ParameterListElement,id:number): Observable<any>{
        return this.httpService.post<DeviceModelAddElement>(this.devicePath+"/model/parameters/"+id,data);
    }

    deleteParameter(id :number): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/model/parameter/'+id);
    }

    //Device Types

    getAllDevicesTypes(): Observable<DeviceTypeListElement[]> {
        return this.httpService.get<DeviceModelListElement[]>(this.devicePath + "/type");
    }

    createDeviceType(data:string): Observable<any>{
        return this.httpService.post<string>(this.devicePath+'/type/',data);
    }

    deleteDeviceTypes(id :number): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/type/'+id);
    }

    //Transfers


    getAllTransfersForCompany(id: number): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath + "/company/" + id);
    }

    getAllTransfers(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath);
    }

    getAllTransfersForUser(username: String): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath + "/user/" + username);
    }


    createTransfer(data: TransferAddElement) {
        return this.httpService.post<TransferAddElement>(this.transferPath,data);
    }
}


