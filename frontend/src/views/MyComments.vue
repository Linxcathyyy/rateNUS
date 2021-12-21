<template>
    <div>
        <v-snackbar top color="success" :value="successSnackbar">Success!</v-snackbar>
        <v-data-table :headers="headers" :items="myComments" sort-by="calories" class="elevation-1" :loading="!isDataReady">
            <template v-slot:top>
              <v-toolbar
                flat
              >
                <v-toolbar-title>
                  <h3>
                    My Comments
                  </h3>
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-dialog
                  v-model="dialog"
                  max-width="500px"
                >
                  <ValidationObserver ref="editCommentObserver">
                    <v-card>
                      <v-card-title>
                        <span class="text-h5" >Edit Comment</span>
                      </v-card-title>
          
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12">{{ editedItem.type }}</v-col>
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
                          <p ref="errorDelete" class="error--text my-2 text-center"></p>
                        </v-col>
                        <v-btn color="orange accent-4" outlined @click="closeDelete" class="mx-2">Cancel</v-btn>
                        <v-btn
                          color="warning" 
                          depressed
                          @click="deleteItemConfirm"
                          :disabled="successSnackbar"
                          :loading="loading"
                          >Confirm</v-btn
                        >
                      </v-row>
                    </v-card-text>
                  </v-card>
                </v-dialog>
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
            },
            defaultItem: {
                id: '',
                type: '',
                rating: 5,
                timestamp: '',
                text: '',
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
            var jwtToken = this.$store.getters.jwtToken;
            await CommentRequest.getCommentsByUserId(this.userId, jwtToken)
                .then(async (response) => {
                    this.myComments = response.data;
                    this.myComments.forEach(this.formatTimestampOfComment);
                })
                .catch((error) => {
                    console.log(error.response.data);
                });
        },
        formatTimestampOfComment(comment) {
          var dateTimeString = "";
          var timestamp = comment.timestamp;
          var dateString = new Date(timestamp).toLocaleDateString();
          var timeString = new Date(timestamp).toLocaleTimeString();
          dateTimeString = dateString + " " + timeString;
          comment["dateTimeString"] = dateTimeString;
        },
        async deleteCommentFromDB(commentId) {
          console.log("commentId: " + commentId);
            await CommentRequest.deleteComment(commentId)
                .then(async (response) => {
                    console.log(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        async updateCommentInDB(commentId, commentObj) {
            await CommentRequest.editComment(commentId, commentObj)
                .then(async (response) => {
                    console.log(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
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
        async deleteItemConfirm() {
            // start loading
            this.loading = true;
            await this.deleteCommentFromDB(this.deletedItem.id)
                .then(async () => {
                    console.log("success");
                    this.myComments.splice(this.editedIndex, 1);
                })
                .catch((error) => {
                    console.log("fail");
                    console.log(error.response);
            });
            this.deletedItem = null;
            this.loading = false;
            this.closeDelete();
        },
        close() {
            // reset error
            this.$refs.editCommentObserver.reset();
            this.dialog = false
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem);
                this.editedIndex = -1;
            })
        },
        closeDelete() {
            this.dialogDelete = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem);
                this.editedIndex = -1;
            })
        },
        async save() {
          const isValidated = await this.validate();
          if (isValidated) {
            await this.updateCommentInDB(this.editedItem.id, this.editedItem.text)
                .then(async () => {
                    console.log("success");
                    if (this.editedIndex > -1) {
                        Object.assign(this.myComments[this.editedIndex], this.editedItem);
                    } else {
                        this.myComments.push(this.editedItem);
                    }
                })
                .catch((error) => {
                    console.log("fail");
                    console.log(error);
            });
            this.close()
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

<style scope></style>
