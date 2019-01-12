import { Component, OnInit } from '@angular/core';
import {UserEdit} from "../../../models/user-edit";
import {UserRoles} from "../../../models/user-roles";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../admin/components/administration/user-management/user.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../shared/services/message.service";
import {I18nService} from "../../../shared/services/i18n/i18n.service";
import {StructureListElement} from "../../../models/structure-elements";
import {ProfileService} from "../profile.service";
import {ProfileEdit} from "../../../models/profile-edit";

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.scss']
})
export class ProfileEditComponent implements OnInit {


    userEditElement: ProfileEdit;

    offices = new Map<string, number>();
    selectedOption: string;

    constructor(
        private route: ActivatedRoute,
        private profileService: ProfileService,
        private officeService: OfficeService,
        private translate: TranslateService,
        private messageService:MessageService,
        private i18nService:I18nService,
        private router: Router) {}

    ngOnInit() {
        this.getOffices();
        this.getProfileEdit();
    }


    getProfileEdit(){
        const id = this.route.snapshot.paramMap.get('id');
        this.profileService.getUserProfileEdit().subscribe(x=>this.userEditElement=x);
    }

    getOffices() {
        this.officeService.getAll().subscribe((response: StructureListElement[]) => {
            this.offices = response.reduce(function (officeMap, office) {
                if (office.id) {
                    officeMap.set(office.name, office.id)
                }
                return officeMap;
            }, this.offices);
        });

    }


    profileUpdate() {
        this.userEditElement.officeId = this.offices.get(this.selectedOption);
        this.profileService.updateAccountByUser(this.userEditElement)
            .subscribe(resp => {
                this.router.navigateByUrl('/profile');
            });
    }

    clear() {
        this.userEditElement = new UserEdit();
    }

}
