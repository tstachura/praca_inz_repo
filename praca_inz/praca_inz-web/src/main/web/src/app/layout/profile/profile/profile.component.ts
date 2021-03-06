import { Component, OnInit } from '@angular/core';
import {ProfileInfo} from "../../../models/profile-info";
import {DeviceListElement} from "../../../models/device-elements";
import {TransferListElement} from "../../../models/transfer-list-element";
import {ProfileService} from "../profile.service";
import {DeviceService} from "../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";


@Component({
  selector: 'app-profile-private',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    user: ProfileInfo;
    devices: DeviceListElement[];
    transfers: TransferListElement[];


  constructor(private profileService:ProfileService,private deviceService : DeviceService,private translate:TranslateService ) { }

    ngOnInit() {
        this.getProfile();
        this.getDevicesForLoggedUser()
        this.getTransfersForLoggedUser()
    }

    getProfile() {
        this.profileService.getUserProfile().subscribe(profile => {
            this.user = profile
        });
    }

    getTransfersForLoggedUser(){
        this.deviceService.getAllTransfersForLoggedUser().subscribe(transferListElement=> {this.transfers=transferListElement});
    }

    getDevicesForLoggedUser(){
        this.deviceService.getAllDevicesForLoggedUser().subscribe(deviceListElement=> {this.devices=deviceListElement});
    }

    getAddress(): string {
        if (this.user.flatNumber == null || this.user.flatNumber === "0") {
            return (this.user.street + ' ' + this.user.houseNumber);
        } else {
            return (this.user.street + ' ' + this.user.houseNumber + ' / ' + this.user.flatNumber);
        }
    }

    getAuthorityTranslation(authority:string):string{
        var tmp:string;
        this.translate.get(authority).subscribe(x=>tmp=x);
        console.log(tmp);
        return tmp;
    }

}
