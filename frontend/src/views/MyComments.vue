<template>
    <div class="my-comments">
        <v-snackbar top color="success" :value="successSnackbar">Success!</v-snackbar>
            <v-dialog
                  v-model="dialog"
                  max-width="500px"
                >
                  <ValidationObserver ref="editCommentObserver">
                    <v-card>
                      <v-card-title class="my-comments-title">
                        <span><h4>Edit Comment </h4></span>
                      </v-card-title>
          
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12"><h3>{{ editedItem.type }} : {{ editedItem.targetName }}</h3></v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="12">
                              <div>
                                <label class="rating-label"> Rating: </label>
                                {{ editedItem.rating }} / 5
                                <v-rating
                                  v-model="editedItem.rating"
                                  background-color="grey"
                                  color="amber"
                                  dense
                                  half-increments
                                  hover
                                  size="30"
                                ></v-rating>
                              </div>
                            </v-col>
                          </v-row>
                            <ValidationProvider
                              name="Comment"
                              rules="required"
                              v-slot="{ errors }"
                            >
                            <v-col cols="12">
                              <v-textarea
                                label="Comment"
                                v-model="editedItem.text"
                                class="comment"
                                type="text"
                                :min-height="30"
                                :max-height="350"
                                auto-grow
                                outlined
                                color="orange accent-4"
                                :error-messages="errors"
                              />
                            </v-col>
                            </ValidationProvider>
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
                            Are you sure you want to delete this comment? <br>
                            This action is irreversible.
                          </p>
                        </v-col>
                        <v-col cols="12">
                          <p ref="deleteError" class="error--text my-2 text-center"></p>
                        </v-col>
                        <v-btn color="orange accent-4" outlined @click="closeDelete" class="mx-2">Cancel</v-btn>
                        <v-btn
                          color="warning" 
                          depressed
                          @click="deleteCommentFromDB"
                          :disabled="successSnackbar"
                          :loading="loading"
                          >Confirm</v-btn
                        >
                      </v-row>
                    </v-card-text>
                  </v-card>
            </v-dialog>
        <v-card>
        <v-card-title class="primary--text">
          <h3>My Comments</h3>
        </v-card-title>
        <v-data-table 
          :headers="headers" 
          :items="myComments" 
          sort-by="calories" 
          class="flat" 
          :loading="!isDataReady"
        >
        <v-progress-linear 
          v-show="!isDataReady" 
          slot="progress" 
          color="orange accent-4" 
          indeterminate>
        </v-progress-linear>
            <template #item.text="{ value }">
                <div class="text-truncate" :style="calculateWidth">
                {{ value }}
                </div>
            </template>
            <template>
              <v-toolbar
                flat
              >
                <v-toolbar-title class="my-comments-title">
                  <h3>
                    My Comments
                  </h3>
                </v-toolbar-title>
              </v-toolbar>
            </template>

      <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">
              mdi-pencil
          </v-icon>
          <v-icon small @click="deleteItem(item)">
              mdi-delete
          </v-icon>
      </template>

        <template v-slot:no-data>
            <div>
                <h4>No comments, start adding one now!</h4>
            </div>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import CommentRequest from "../httpRequests/CommentRequest";
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
    name: "MyComments",
    components: {
      ValidationProvider,
      ValidationObserver,
    },

    data() {
        return {
            myComments: [],
            userId: "",
            isDataReady: false,
            loading: false,
            successSnackbar: false,
            dialog: false,
            dialogDelete: false,
            headers: [
                { text: 'Type', align: 'start', value: 'type' },
                { text: 'name', value: 'targetName' },
                { text: 'Rating', value: 'rating' },
                { text: 'Posted on', value: 'dateTimeString' },
                { text: 'Comment', value: 'text', sortable: false },
                { text: 'Actions', value: 'actions', sortable: false },
            ],
            desserts: [],
            editedIndex: -1,
            deletedItem: null,
            editedItem: {
                id: '',
                type: '',
                rating: 5,
                timestamp: '',
                text: '',
                targetName: '',
            },
            defaultItem: {
                id: '',
                type: '',
                rating: 5,
                timestamp: '',
                text: '',
                targetName: '',
            },

        };
    },
    methods: {
        async validate() {
          return this.$refs.editCommentObserver.validate();
        },
        getCurrentUser() {
            this.userId = this.$store.getters.id;
        },
        async getCommentsByUserId() {
            const jwtToken = this.$store.getters.jwtToken;
            await CommentRequest.getCommentsByUserId(this.userId, jwtToken)
                .then(async (response) => {
                    this.myComments = response.data;
                    console.log(this.myComments);
                    this.myComments.forEach(this.formatTimestampOfComment);
                    this.myComments.forEach(this.formatType);
                })
                .catch((error) => {
                    console.log(error.response.data);
                });
        },
        formatType(comment) {
          if (comment.type == "hostel") {
              comment["type"] = "Hostel";
          } else if (comment.type == "stall") {
              comment["type"] = "Food";
          } else {
              comment["type"] = "Study Area";
          }
        },
        formatTimestampOfComment(comment) {
          var dateTimeString = "";
          var timestamp = comment.timestamp;
          var dateString = new Date(timestamp).toLocaleDateString();
          var timeString = new Date(timestamp).toLocaleTimeString();
          dateTimeString = dateString + " " + timeString;
          comment["dateTimeString"] = dateTimeString;
        },
        async updateCommentInDB(commentId, comment, rating) {
          const jwtToken = this.$store.getters.jwtToken;
          const username = this.$store.getters.fullName;
          const result = await CommentRequest.editComment(commentId, comment, rating, jwtToken, username);
          return result.status;
        },
        editItem(item) {
            this.editedIndex = this.myComments.indexOf(item);
            this.editedItem = Object.assign({}, item);
            this.dialog = true;
        },
        deleteItem(item) {
            this.editedIndex = this.desserts.indexOf(item);
            this.editedItem = Object.assign({}, item);
            this.dialogDelete = true;
            this.deletedItem = item;
        },
        async deleteCommentFromDB() {
            // start loading
            this.loading = true;
            const jwtToken = this.$store.getters.jwtToken;
            const username = this.$store.getters.fullName;
            await CommentRequest.deleteComment(this.deletedItem.id, jwtToken, username)
                .then(async (response) => {
                    console.log(response);
                    console.log("success");
                    this.successSnackbar = true;
                    this.myComments.splice(this.editedIndex, 1);
                    setTimeout(() => (this.successSnackbar = false), 1000);
                    this.deletedItem = null;
                    this.closeDelete();
                    await this.refreshPage();
                })
                .catch((error) => {
                    console.log("fail");
                    console.log(error.toString());
                    this.$refs.deleteError.innerHTML = "Failed to delete this comment, please try again";
                });
            this.loading = false;
        },
        close() {
            // reset error
            this.$refs.editCommentObserver.reset();
            this.$refs.saveError.innerHTML = "";
            this.dialog = false
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem);
                this.editedIndex = -1;
            })
        },
        closeDelete() {
            this.$refs.deleteError.innerHTML = "";
            this.dialogDelete = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem);
                this.editedIndex = -1;
            })
        },
        async save() {
          // start loading
          this.loading = true;
          const isValidated = await this.validate();
          if (isValidated) {
            await this.updateCommentInDB(this.editedItem.id, this.editedItem.text, this.editedItem.rating)
                .then(async (response) => {
                  if (response === 200) {
                    console.log("success");
                    if (this.editedIndex > -1) {
                        Object.assign(this.myComments[this.editedIndex], this.editedItem);
                    } else {
                        this.myComments.push(this.editedItem);
                    }
                    this.close();
                    this.successSnackbar = true;
                    setTimeout(() => (this.successSnackbar = false), 1000);
                    await this.refreshPage();
                  } else {
                    console.log("fail");
                    this.$refs.saveError.innerHTML = "Failed to update this comment, please try again";
                  }
                })
                .catch((error) => {
                    console.log("fail");
                    this.$refs.saveError.innerHTML = "Failed to update this comment, please try again";
                    console.log(error);
            });
            this.loading = false;
          }
        },
        async refreshPage() {
          this.isDataReady = false;
          this.getCurrentUser();
          await this.getCommentsByUserId();
          this.isDataReady = true;
        }
    },

    computed: {
      calculateWidth() {
        switch (this.$vuetify.breakpoint.name) {
          case "xs":
            return "max-width: 150px"
          case "sm":
            return "max-width: 200px"
          case "md":
            return "max-width: 300px"
          case "lg":
            return "max-width: 300px"
          case "xl":
            return "max-width: 400px"
          default:
            return "max-width: 400px"
        }
      },
    },

    watch: {
        dialog(val) {
            val || this.close()
        },
        dialogDelete(val) {
            val || this.closeDelete()
        },
    },

  async created() {
    this.getCurrentUser();
    await this.getCommentsByUserId();
    this.isDataReady = true;
  },
};
</script>

<style scope>
.my-comments {
  /* margin-top: 4rem; */
}
.my-comments-title {
  color: #ff6d00;
}
</style>
