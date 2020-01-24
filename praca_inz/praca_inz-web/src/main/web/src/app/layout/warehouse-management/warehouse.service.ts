import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {ExternalTransferListElement, WarehouseAddElement, WarehouseEditElement, WarehouseListElement, WarehouseViewElement} from "../../models/warehouse-elements";
import {StructureEditElement} from "../../models/structure-elements";
import {UserListElement} from "../../models/user-list-element";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

    private warehousePath = this.configuration.ServerWithApiUrl + '/warehouse';
    private deliveryPath = this.configuration.ServerWithApiUrl + '/external/transfer';


    constructor(private httpService: HttpService, private configuration: Configuration) { }

    //Warehouses

    getAll(): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath);
    }

    getAllForTransfer(){
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/transfer');
    }

    getAllForTransferRequest(id:number){
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/transfer-request/'+id);
    }

    getAllForWarehouseman(): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/warehouseman');
    }

    getAllForCompany(id:number): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/company/'+id);
    }

    getAllForDepartment(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/department/'+id);
    }

    getAllForOffice(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/office/'+id);
    }

    getWarehouse(id:string):Observable<WarehouseViewElement>{
        return this.httpService.get<WarehouseViewElement>(this.warehousePath+'/'+id);
    }

    getWarehouseEdit(id:string): Observable<WarehouseEditElement>{
        return this.httpService.get<WarehouseEditElement>(this.warehousePath+'/edit/'+id);
    }

    createWarehouse(data: WarehouseAddElement): Observable<any>  {
        return this.httpService.post<WarehouseAddElement>(this.warehousePath, data);
    }

    updateWarehouse(data: WarehouseEditElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.warehousePath, data);
    }

    deleteWarehouse(id :string): Observable<any>{
        return this.httpService.delete<any>(this.warehousePath+'/'+id);
    }



    //Deliveries

    getAllDeliveries(): Observable<ExternalTransferListElement[]> {
        return this.httpService.get<ExternalTransferListElement[]>(this.deliveryPath);
    }

    getAllDeliveriesForCompany(id:number): Observable<ExternalTransferListElement[]> {
        return this.httpService.get<ExternalTransferListElement[]>(this.deliveryPath+'/company/'+id);
    }

    getAllDeliveriesForWarehouse(): Observable<ExternalTransferListElement[]> {
        return this.httpService.get<ExternalTransferListElement[]>(this.deliveryPath+'/warehouseman');
    }

    confirmDelivery(id: number) {
        return this.httpService.put<any>(this.deliveryPath+'/confirm/'+id,{})
    }


    //Warehouseman

    getAllWarehouseUsers(id: string): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.warehousePath + '/users/' + id);
    }

    attachWarehouseUser(userId:number,warehouseId:number){
        return this.httpService.post<any>(this.warehousePath+'/user/'+warehouseId+'/'+userId,{});
    }

    detachWarehouseUser(userId:number,warehouseId:number): Observable<any>{
        return this.httpService.delete<any>(this.warehousePath+'/user/'+warehouseId+'/'+userId);
    }
}
