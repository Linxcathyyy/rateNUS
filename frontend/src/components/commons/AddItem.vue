<template>
  <div>
    <ValidationObserver ref="addItemObserver">
                    <v-card>
                      <v-card-title class="my-comments-title">
                        <span><h4> Add {{ type }} </h4></span>
                      </v-card-title>
          
                      <v-card-text>
                        <v-container>
                          <ValidationProvider
                              name="Name"
                              rules="required"
                              v-slot="{ errors }"
                            >
                            <v-row no-gutters>
                                <v-col cols="12">
                                    <v-text-field
                                    label="Name"
                                    v-model="item.name"
                                    type="text"
                                    :error-messages="errors"
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                          </ValidationProvider>
                          <ValidationProvider
                              name="Location"
                              rules="required"
                              v-slot="{ errors }"
                            >
                            <v-row no-gutters>
                                <v-col cols="12">
                                    <v-text-field
                                    label="Location"
                                    v-model="item.location"
                                    type="text"
                                    :error-messages="errors"
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                          </ValidationProvider>
                          <div v-if="type == 'Hostel'">
                            <ValidationProvider
                                name="Facilities"
                                rules="required"
                                v-slot="{ errors }"
                                >
                                <v-row no-gutters>
                                    <v-col cols="12">
                                        <v-select
                                            label="Facilities"
                                            multiple
                                            :items="defaultFacilities"
                                            v-model="item.facilities"
                                            :error-messages="errors"
                                        ></v-select>
                                        <p ref="facilitiesError" class="error--text"></p>
                                    </v-col>
                                </v-row>
                            </ValidationProvider>
                          </div>
                          <div v-if="type == 'Stall'">
                                <v-row no-gutters>
                                    <v-col cols="5">
                                        <ValidationProvider
                                            name="Lowest Price"
                                            rules="required"
                                            v-slot="{ errors }"
                                        >
                                            <v-text-field
                                            label="Lowest Price"
                                            v-model="item.lowestPrice"
                                            type="number"
                                            :error-messages="errors"
                                            ></v-text-field>
                                        </ValidationProvider>
                                    </v-col>
                                    <v-spacer></v-spacer>
                                    <v-col cols="5">
                                        <ValidationProvider
                                            name="Highest Price"
                                            rules="required"
                                            v-slot="{ errors }"
                                        >
                                            <v-text-field
                                            label="Highest Price"
                                            v-model="item.highestPrice"
                                            type="number"
                                            :error-messages="errors"
                                            ></v-text-field>
                                        </ValidationProvider>
                                    </v-col>
                                </v-row>
                         </div>
                         <ValidationProvider
                              name="ImageUrl"
                              rules="required"
                              v-slot="{ errors }"
                            >
                            <v-row no-gutters>
                                <v-col cols="12">
                                <v-textarea
                                    label="ImageUrl"
                                    v-model="item.imageUrl"
                                    type="text"
                                    :min-height="30"
                                    :max-height="350"
                                    auto-grow
                                    outlined
                                    color="orange accent-4"
                                    :error-messages="errors"
                                />
                                </v-col>
                            </v-row>
                          </ValidationProvider>
                          <div v-if="type != 'Study Area'">
                            <ValidationProvider
                                name="Description"
                                rules="required"
                                v-slot="{ errors }"
                                >
                                <v-row>
                                    <v-col cols="12">
                                    <v-textarea
                                        label="Description"
                                        v-model="item.description"
                                        type="text"
                                        :min-height="30"
                                        :max-height="350"
                                        auto-grow
                                        outlined
                                        color="orange accent-4"
                                        :error-messages="errors"
                                    />
                                    </v-col>
                                </v-row>
                            </ValidationProvider>
                          </div>
                        </v-container>
                      </v-card-text>
          
                      <p ref="saveError" class="error--text"></p>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          color="orange accent-4"
                          text
                          @click="close"
                        >
                          Cancel
                        </v-btn>
                        <v-btn
                          color="warning" 
                          depressed
                          @click="save"
                          :loading="loading"
                          >Save</v-btn
                        >
                      </v-card-actions>
                    </v-card>
    </ValidationObserver>
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import StallRequest from "../../httpRequests/StallRequest";
import StudyAreaRequest from "../../httpRequests/StudyAreaRequest";
import {
  ValidationProvider,
  extend,
  ValidationObserver,
  setInteractionMode,
} from "vee-validate";
import { required } from "vee-validate/dist/rules";
extend("required", {
  ...required,
  message: "{_field_} cannot be empty",
});
setInteractionMode("passive");

export default {
    name: "AddItem",
    components: {
      ValidationProvider,
      ValidationObserver,
    },
    props: {
        type: String,
    },

    data() {
        return {
            defaultFacilities: ["badmintonCourt", "basketballCourt", "carPark", "campusSecurity", "convenienceStore", "danceStudio", "fitnessCentre",
                            "laundry", "lounge", "meetingRoom", "multipurposeHall", "musicRoom", "pantry", "poolRoom", "seminarRoom", "squashCourt",
                            "studyRoom", "swimmingPool", "tennisCourt", "wifi"],
            userId: "",
            loading: false,
            item: {
                name: null,
                location: null,
                facilities: null,
                description: null,
                imageUrl: null,
                lowestPrice: null,
                highestPrice: null,
            },

        };
    },
    methods: {
        async validate() {
          return this.$refs.addItemObserver.validate();
        },
        async addItemInDB(data) {
          const jwtToken = this.$store.getters.jwtToken;
          const username = this.$store.getters.fullName;
          var result;
          switch (this.type) {
              case "Hostel":
                    result = await HostelRequest.addHostel(jwtToken, username, data);
                    break;
              case "Stall":
                    result = await StallRequest.addStall(jwtToken, username, data);
                    break;
              case "Study Area":
                    result = await StudyAreaRequest.addStudyArea(jwtToken, username, data);
                    break;
          }
          return result.status;
        },
        close() {
            // reset error
            this.$refs.addItemObserver.reset();
            this.$refs.saveError.innerHTML = "";
            this.$emit("closeAddDialog", true);
        },
        async save() {
          // start loading
          const isValidated = await this.validate();
          if (isValidated) {
            this.loading = true;
            const imageUrl = this.item.imageUrl.toString().split(",");
            const facilities = this.item.facilities ? this.item.facilities.toString().split(",") : [];
            const data = {
                name: this.item.name,
                location: this.item.location,
                imageUrl: imageUrl,
                ...(this.item.facilities && {facilities: facilities}),
                ...(this.item.description && {description: this.item.description}),
                ...(this.item.lowestPrice && {lowestPrice: Number(this.item.lowestPrice)}),
                ...(this.item.highestPrice && {highestPrice: Number(this.item.highestPrice)})
            }
            
            await this.addItemInDB(data)
                .then(async (response) => {
                  if (response === 200) {
                    console.log("success");
                    this.$emit("success", true);
                    this.close();
                  } else {
                    console.log("fail");
                    this.$refs.saveError.innerHTML = "Failed to add this item, please try again";
                  }
                })
                .catch((error) => {
                    console.log("fail");
                    this.$refs.saveError.innerHTML = "Failed to add this item, please try again";
                    console.log(error);
            });
            this.loading = false;
          }
        },
    },
};
</script>

