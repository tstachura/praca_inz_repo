import {Component, OnInit} from '@angular/core';
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeviceModelListElement} from "../../../../../../models/device-elements";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";

@Component({
    selector: 'app-device-model-list',
    templateUrl: './device-model-list.component.html',
    styleUrls: ['./device-model-list.component.scss']
})
export class DeviceModelListComponent implements OnInit {

    deviceModels: DeviceModelListElement[];

    constructor(
        private deviceService: DeviceService,
        private translate: TranslateService,
        private userService: UserService,
        private configuration: Configuration,
        private messageService: MessageService
    ) {
    }

    ngOnInit() {
        this.getDevicesModels()
    }


    getDevicesModels() {
        this.deviceService.getAllDevicesModels().subscribe(deviceListElement => {
            this.deviceModels = deviceListElement
        });
    }

    filterDeviceModels(searchText: string) {
        this.deviceService.getAllDevicesModels().subscribe(deviceModels => {
            if (!deviceModels) {
                this.deviceModels = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.deviceModels = deviceModels;
            }

            searchText = searchText.toLowerCase();
            this.deviceModels = deviceModels.filter(it => {
                const range = it.name + ' ' + it.manufacture + ' ' + it.deviceTypeName;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(deviceModel: DeviceModelListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.model.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);

        if (deviceModel.numberOfDevices == 0) {

            this.messageService
                .confirm(entity, message, yes, no)
                .subscribe(confirmed => {
                    if (confirmed) {
                        this.deviceService.deleteDeviceModel(String(deviceModel.id)).subscribe(resp => {
                            this.getDevicesModels()
                            this.translate.get('success.device.model.delete').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                    }
                });
        } else {
            this.translate.get("device.model.used").subscribe(x=>{
                this.messageService.error(x)
            });
        }
    }
}
