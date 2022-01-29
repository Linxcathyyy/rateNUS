<template>
  <div class="my-comments mt-6">
    <v-snackbar top color="success" :value="successSnackbar"
      >Success!</v-snackbar
    >
    <v-dialog v-model="dialog" max-width="500px">
      <ValidationObserver ref="editItemObserver">
        <v-card>
          <v-card-title class="my-comments-title">
            <span
              ><h4>Edit {{ type }}</h4></span
            >
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
                      v-model="editedItem.name"
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
                      v-model="editedItem.location"
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
                        v-model="editedItem.facilities"
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
                        v-model="editedItem.lowestPrice"
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
                        v-model="editedItem.highestPrice"
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
                      v-model="editedItem.imageUrl"
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
                        v-model="editedItem.description"
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
            <v-btn color="orange accent-4" text @click="close"> Cancel </v-btn>
            <v-btn
              color="warning"
              depressed
              @click="save"
              :disabled="successSnackbar"
              :loading="loading"
              >Save</v-btn
            >
          </v-card-actions>
        </v-card>
      </ValidationObserver>
    </v-dialog>
    <v-dialog v-model="dialogDelete" max-width="500px">
      <v-card>
        <v-card-title class="warning white--text">Warning</v-card-title>
        <v-card-text class="pt-6">
          <v-row justify="center" align="center">
            <v-col cols="12">
              <p class="text--secondary text-center">
                Are you sure you want to delete this item? <br />
                This action is irreversible.
              </p>
            </v-col>
            <v-col cols="12">
              <p ref="deleteError" class="error--text my-2 text-center"></p>
            </v-col>
            <v-btn
              color="orange accent-4"
              outlined
              @click="closeDelete"
              class="mx-2"
              >Cancel</v-btn
            >
            <v-btn
              color="warning"
              depressed
              @click="deleteItemFromDB"
              :disabled="successSnackbar"
              :loading="loading"
              >Confirm</v-btn
            >
          </v-row>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog v-model="addItemDialog" max-width="500px">
      <AddItem
        :type="type"
        @success="toggleSnackbar()"
        @closeAddDialog="addItemDialog = false"
      />
    </v-dialog>
    <v-card>
      <v-row>
        <v-card-title class="primary--text ml-2">
          <h3>Manage {{ type }}</h3>
        </v-card-title>
        <v-spacer></v-spacer>
        <v-btn
          class="mr-10 my-4"
          fab
          dark
          small
          depressed
          color="primary"
          @click="addItemDialog = true"
        >
          <v-icon dark> mdi-plus </v-icon>
        </v-btn>
      </v-row>
      <v-data-table
        :headers="headers"
        :items="myItems"
        sort-by="name"
        class="flat"
        :loading="!isDataReady"
      >
        <v-progress-linear
          v-show="!isDataReady"
          slot="progress"
          color="orange accent-4"
          indeterminate
        >
        </v-progress-linear>
        <template #item.text="{ value }">
          <div class="text-truncate" :style="calculateWidth">
            {{ value }}
          </div>
        </template>

        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">
            mdi-pencil
          </v-icon>
          <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
        </template>

        <template v-slot:no-data>
          <div>
            <h4>No {{ type }}, start adding one now!</h4>
          </div>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import StallRequest from "../../httpRequests/StallRequest";
import StudyAreaRequest from "../../httpRequests/StudyAreaRequest";
import AddItem from "./AddItem.vue";
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
  name: "ManageItems",
  components: {
    ValidationProvider,
    ValidationObserver,
    AddItem,
  },
  props: {
    type: String,
  },

  data() {
    return {
      defaultFacilities: [
        "badmintonCourt",
        "basketballCourt",
        "carPark",
        "campusSecurity",
        "convenienceStore",
        "danceStudio",
        "fitnessCentre",
        "laundry",
        "lounge",
        "meetingRoom",
        "multipurposeHall",
        "musicRoom",
        "pantry",
        "poolRoom",
        "seminarRoom",
        "squashCourt",
        "studyRoom",
        "swimmingPool",
        "tennisCourt",
        "wifi",
      ],
      myItems: [],
      userId: "",
      isDataReady: false,
      loading: false,
      successSnackbar: false,
      dialog: false,
      dialogDelete: false,
      headers: [
        { text: "Name", align: "start", value: "name" },
        { text: "Rating", value: "rating" },
        { text: "Location", value: "location" },
        { text: "Comment Count", value: "commentCount" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      desserts: [],
      editedIndex: -1,
      deletedItem: null,
      hostelCount: 0,
      stallCount: 0,
      studyAreaCount: 0,
      addItemDialog: false,
      editedItem: {
        id: "",
        name: "",
        rating: -1,
        location: "",
        facilities: [],
        description: "",
        imageUrl: [],
        lowestPrice: null,
        highestPrice: null,
      },
      defaultItem: {
        id: "",
        name: "",
        rating: -1,
        location: "",
        facilities: [],
        description: "",
        imageUrl: [],
        lowestPrice: null,
        highestPrice: null,
      },
    };
  },
  methods: {
    async validate() {
      return this.$refs.editItemObserver.validate();
    },
    async getTotalCount() {
      switch (this.type) {
        case "Hostel":
          await HostelRequest.getHostelList(0, 1)
            .then(async (response) => {
              this.hostelCount = response.data.totalElements;
            })
            .catch(() => {});
          break;
        case "Stall":
          await StallRequest.getStallList(0, 1)
            .then(async (response) => {
              this.stallCount = response.data.totalElements;
            })
            .catch(() => {});
          break;
        case "Study Area":
          await StudyAreaRequest.getStudyAreaList(0, 5)
            .then(async (response) => {
              this.studyAreaCount = response.data.totalElements;
            })
            .catch(() => {});
          break;
      }
    },
    // for admin
    async getAllItems() {
      switch (this.type) {
        case "Hostel":
          await HostelRequest.getHostelList(0, this.hostelCount)
            .then(async (response) => {
              this.hostelCount = response.data.totalElements;
              // console.log("data: " + response.data.totalElements);
              this.myItems = response.data.content;
              // console.log(this.myItems);
            })
            .catch(() => {});
          break;
        case "Stall":
          await StallRequest.getStallList(0, this.stallCount)
            .then(async (response) => {
              this.myItems = response.data.content;
              // console.log(this.myItems);
            })
            .catch(() => {});
          break;
        case "Study Area":
          await StudyAreaRequest.getStudyAreaList(0, this.studyAreaCount)
            .then(async (response) => {
              this.myItems = response.data.content;
              // console.log(this.myItems);
            })
            .catch(() => {});
          break;
      }
    },
    getCurrentUser() {
      this.userId = this.$store.getters.id;
    },
    async updateItemInDB(itemId, data) {
      const jwtToken = this.$store.getters.jwtToken;
      const username = this.$store.getters.fullName;
      var result;
      switch (this.type) {
        case "Hostel":
          result = await HostelRequest.updateHostel(
            itemId,
            jwtToken,
            username,
            data
          );
          break;
        case "Stall":
          result = await StallRequest.updateStall(
            itemId,
            jwtToken,
            username,
            data
          );
          break;
        case "Study Area":
          result = await StudyAreaRequest.updateStudyArea(
            itemId,
            jwtToken,
            username,
            data
          );
          break;
      }
      return result.status;
    },
    editItem(item) {
      this.editedIndex = this.myItems.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
      this.deletedItem = item;
    },
    async deleteHostel(id, jwtToken, username) {
      await HostelRequest.deleteHostel(id, jwtToken, username)
        .then(async (response) => {
          // console.log(response);
          // console.log("success");
          this.successSnackbar = true;
          this.myItems.splice(this.editedIndex, 1);
          setTimeout(() => (this.successSnackbar = false), 1000);
          this.deletedItem = null;
          this.closeDelete();
          await this.refreshPage();
          return response.data;
        })
        .catch(() => {
          // console.log("fail");

          this.$refs.deleteError.innerHTML =
            "Failed to delete this hostel, please try again";
        });
      this.loading = false;
    },
    async deleteStall(id, jwtToken, username) {
      await StallRequest.deleteStall(id, jwtToken, username)
        .then(async (response) => {
          // console.log(response);
          // console.log("success");
          this.successSnackbar = true;
          this.myItems.splice(this.editedIndex, 1);
          setTimeout(() => (this.successSnackbar = false), 1000);
          this.deletedItem = null;
          this.closeDelete();
          await this.refreshPage();
          return response.data;
        })
        .catch(() => {
          // console.log("fail");

          this.$refs.deleteError.innerHTML =
            "Failed to delete this hostel, please try again";
        });
      this.loading = false;
    },
    async deleteStudyArea(id, jwtToken, username) {
      await StudyAreaRequest.deleteStudyArea(id, jwtToken, username)
        .then(async (response) => {
          // console.log(response);
          // console.log("success");
          this.successSnackbar = true;
          this.myItems.splice(this.editedIndex, 1);
          setTimeout(() => (this.successSnackbar = false), 1000);
          this.deletedItem = null;
          this.closeDelete();
          await this.refreshPage();
          return response.data;
        })
        .catch(() => {
          // console.log("fail");

          this.$refs.deleteError.innerHTML =
            "Failed to delete this hostel, please try again";
        });
      this.loading = false;
    },
    async deleteItemFromDB() {
      // start loading
      this.loading = true;
      const jwtToken = this.$store.getters.jwtToken;
      const username = this.$store.getters.fullName;
      switch (this.type) {
        case "Hostel":
          await this.deleteHostel(this.deletedItem.id, jwtToken, username);
          break;
        case "Stall":
          await this.deleteStall(this.deletedItem.id, jwtToken, username);
          break;
        case "Study Area":
          await this.deleteStudyArea(this.deletedItem.id, jwtToken, username);
          break;
      }
    },
    close() {
      // reset error
      this.$refs.editItemObserver.reset();
      this.$refs.saveError.innerHTML = "";
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    async toggleSnackbar() {
      this.successSnackbar = true;
      setTimeout(() => (this.successSnackbar = false), 1000);
      await this.refreshPage();
    },
    closeDelete() {
      this.$refs.deleteError.innerHTML = "";
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    async save() {
      // start loading
      const isValidated = await this.validate();
      if (isValidated) {
        this.loading = true;
        const imageUrl = this.editedItem.imageUrl.toString().split(",");
        const facilities = this.editedItem.facilities
          ? this.editedItem.facilities.toString().split(",")
          : [];
        const data = {
          name: this.editedItem.name,
          location: this.editedItem.location,
          imageUrl: imageUrl,
          ...(this.editedItem.facilities && { facilities: facilities }),
          ...(this.editedItem.description && {
            description: this.editedItem.description,
          }),
          ...(this.editedItem.lowestPrice && {
            lowestPrice: Number(this.editedItem.lowestPrice),
          }),
          ...(this.editedItem.highestPrice && {
            highestPrice: Number(this.editedItem.highestPrice),
          }),
        };

        await this.updateItemInDB(this.editedItem.id, data)
          .then(async (response) => {
            if (response === 200) {
              // console.log("success");
              if (this.editedIndex > -1) {
                Object.assign(this.myItems[this.editedIndex], this.editedItem);
              } else {
                this.myItems.push(this.editedItem);
              }
              this.close();
              this.successSnackbar = true;
              setTimeout(() => (this.successSnackbar = false), 1000);
              // await this.refreshPage();
            } else {
              // console.log("fail");
              this.$refs.saveError.innerHTML =
                "Failed to update this item, please try again";
            }
          })
          .catch(() => {
            // console.log("fail");
            this.$refs.saveError.innerHTML =
              "Failed to update this item, please try again";
          });
        this.loading = false;
      }
    },
    async refreshPage() {
      this.isDataReady = false;
      this.getCurrentUser();
      await this.getTotalCount();
      await this.getAllItems();
      this.isDataReady = true;
    },
  },

  computed: {
    calculateWidth() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return "max-width: 150px";
        case "sm":
          return "max-width: 200px";
        case "md":
          return "max-width: 300px";
        case "lg":
          return "max-width: 300px";
        case "xl":
          return "max-width: 400px";
        default:
          return "max-width: 400px";
      }
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  async created() {
    this.getCurrentUser();
    await this.getTotalCount();
    await this.getAllItems();
    this.isDataReady = true;
  },
};
</script>

<style scope>
.my-comments-title {
  color: #ff6d00;
}
</style>
