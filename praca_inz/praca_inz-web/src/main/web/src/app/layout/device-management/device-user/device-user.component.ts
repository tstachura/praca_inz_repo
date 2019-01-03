import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {DeviceService} from "../device.service";
import {DeviceListElement} from "../../../models/device-elements";

@Component({
    selector: 'app-device-user',
    templateUrl: './device-user.component.html',
    styleUrls: ['./device-user.component.scss']
})
export class DeviceUserComponent implements OnInit {


    devices: DeviceListElement[];

    constructor(private deviceService : DeviceService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getDevicesForLoggedUser()
    }


    getDevicesForLoggedUser(){
        this.deviceService.getAllDevicesForLoggedUser().subscribe(deviceListElement=> {this.devices=deviceListElement});
    }

    filterDevices(searchText: string) {
        this.deviceService.getAllDevicesForLoggedUser().subscribe(devices => {
            if (!devices) {
                this.devices = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.devices = devices;
            }

            searchText = searchText.toLowerCase();
            this.devices = devices.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }


    transfer(device: DeviceListElement) {
        // const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        // modalRef.componentInstance.user = user;
        // modalRef.result.then(
        //     result => {
        //         // Left blank intentionally, nothing to do here
        //     },
        //     reason => {
        //         // Left blank intentionally, nothing to do here
        //     }
        // );
    }
}
