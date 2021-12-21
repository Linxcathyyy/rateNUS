<template>
  <div>
    <v-snackbar top color="success" :value="successSnackbar"
      >Success!</v-snackbar
    >
    <v-data-table
      :headers="headers"
      :items="myComments"
      sort-by="calories"
      class="elevation-1"
      :loading="!isDataReady"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>
            <h3>My Comments</h3>
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="text-h5">Edit Comment</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.type"
                        label="Dessert name"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.calories"
                        label="Calories"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.fat"
                        label="Fat (g)"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.carbs"
                        label="Carbs (g)"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.protein"
                        label="Protein (g)"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">
                  Cancel
                </v-btn>
                <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="warning white--text">Warning</v-card-title>
              <v-card-text class="pt-6">
                <v-row justify="center" align="center">
                  <v-col cols="12">
                    <p class="text--secondary text-center">
                      Are you sure you want to delete this comment? <br />
                      This action is irreversible.
                    </p>
                  </v-col>
                  <v-col cols="12">
                    <p
                      ref="errorDelete"
                      class="error--text my-2 text-center"
                    ></p>
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
        <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
        <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
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

export default {
  name: "MyComments",
  components: {},

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
        { text: "Type", align: "start", value: "type" },
        { text: "Rating", value: "rating" },
        { text: "Posted on", value: "dateTimeString" },
        { text: "Comment", value: "text", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
      desserts: [],
      editedIndex: -1,
      editedItem: {
        type: "",
        rating: "",
        dateTimeString: "",
        text: "",
      },
      defaultItem: {
        type: "",
        rating: "",
        dateTimeString: "",
        text: "",
      },
    };
  },
  methods: {
    getCurrentUser() {
      this.userId = this.$store.getters.id;
    },
    async getCommentsByUserId() {
      await CommentRequest.getCommentsByUserId(this.userId, 0, 20)
        .then(async (response) => {
          this.myComments = response.data.content;
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
    async deleteComment(commentId) {
      await CommentRequest.deleteComment(commentId)
        .then(async (response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },
    async editComment(commentId) {
      await CommentRequest.deleteComment(commentId)
        .then(async (response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error.response.data);
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
    },
    deleteItemConfirm() {
      this.myComments.splice(this.editedIndex, 1);
      this.closeDelete();
    },
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.myComments[this.editedIndex], this.editedItem);
      } else {
        this.myComments.push(this.editedItem);
      }
      this.close();
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
    await this.getCommentsByUserId();
    this.isDataReady = true;
  },
};
</script>

<style scope></style>
