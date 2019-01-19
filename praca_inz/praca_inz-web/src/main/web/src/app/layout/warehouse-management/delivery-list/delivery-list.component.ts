import {Component, OnInit} from '@angular/core';
import {TransferListElement} from "../../../models/transfer-list-element";
import {DeviceService} from "../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeliveryListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../warehouse.service";
import {DeviceAddElement} from "../../../models/device-elements";
import {MessageService} from "../../../shared/services/message.service";

@Component({
    selector: 'app-delivery-list',
    templateUrl: './delivery-list.component.html',
    styleUrls: ['./delivery-list.component.scss']
})
export class DeliveryListComponent implements OnInit {

    deliveries: DeliveryListElement[];

    constructor(
        private warehouseService: WarehouseService,
        private messageService: MessageService,
        private deviceService: DeviceService,
        private translate: TranslateService) {
    }

    ngOnInit() {
        this.filterDeliveries(null)
    }

    filterDeliveries(searchText: string) {
        this.warehouseService.getAllDeliveriesForWarehouse().subscribe(transfers => {
            if (!transfers) {
                this.deliveries = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.deliveries = transfers;
            }

            searchText = searchText.toLowerCase();
            this.deliveries = transfers.filter(it => {
                const range = it.serialNumber + ' ' + it.receiver + ' ' + it.sender + ' ' + it.deliveryNumber + ' ' + it.deviceModelName + ' ' + it.lastUpdate + ' ' + it.createDate + ' ' + it.title + ' ' + it.username;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    confirm(deliver: DeliveryListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('delivery.confirm').subscribe(x => entity = x);
        this.translate.get('confirm.recieve').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    var device: DeviceAddElement = new DeviceAddElement();
                    device.warehouseId = deliver.warehouseId;
                    device.serialNumber = deliver.serialNumber;
                    device.deviceModelId = deliver.deviceModelId;
                    device.companyId = deliver.companyId;
                    this.warehouseService.confirmDelivery(deliver.id).subscribe(rep => {
                        this.deviceService.createDevice(device).subscribe(rep => {
                            this.filterDeliveries(null);
                            this.translate.get('success.delivery.confirm').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                    });
                }
            });
    }
}
