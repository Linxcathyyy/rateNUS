<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="successDialog" persistent max-width="290">
        <v-card>
          <v-card-title class="text-h5"> Success </v-card-title>
          <v-card-text>You have added a comment successfully!</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="darken-1"
              class="orange accent-4"
              dark
              rounded
              text
              @click="refreshPage"
            >
              Ok
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <v-snackbar top color="red" :value="failureSnackbar"
      >An unknown error has occured, please try again!</v-snackbar
    >
    <v-snackbar top color="red" :value="notLoggedInSnackbar"
      >You must be logged in to post comments. Please log in and try
      again!</v-snackbar
    >
    <v-card flat class="my-12">
      <div class="comment-form">
        <v-form>
          <div>
            <label class="rating-label"> Rating: </label>
            {{ rating }} / 5

            <v-rating
              v-model="rating"
              background-color="grey"
              color="amber"
              dense
              half-increments
              hover
              size="30"
            ></v-rating>
          </div>
          <ValidationObserver ref="addCommentObserver">
            <ValidationProvider
              name="Comment"
              rules="required"
              v-slot="{ errors }"
            >
              <v-textarea
                :label="isCommentDisabled ? 'Log in to comment' : 'Comment'"
                v-model="comment"
                class="comment"
                placeholder="Join the discussion..."
                type="text"
                :min-height="30"
                :max-height="350"
                auto-grow
                outlined
                color="orange accent-4"
                :error-messages="errors"
                :disabled="isCommentDisabled"
              />
            </ValidationProvider>
            <v-layout>
              <v-spacer></v-spacer>
              <v-btn
                outlined
                color="orange accent-4"
                @click="handleSubmit(comment, rating)"
              >
                Submit
              </v-btn>
            </v-layout>
          </ValidationObserver>
        </v-form>
      </div>
    </v-card>
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
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
  components: {
    ValidationProvider,
    ValidationObserver,
  },
  data() {
    return {
      comment: "",
      rating: 5,
      rowsNum: 1,
      isExpanded: false,
      successDialog: false,
      failureSnackbar: false,
      notLoggedInSnackbar: false,
      isCommentDisabled: false, // should be changed to true if not logged in
    };
  },
  methods: {
    async validate() {
      return this.$refs.addCommentObserver.validate();
    },
    refreshPage() {
      this.successDialog = false;
      // reset comment inputs
      this.comment = "";
      this.rating = 5;
      // reload current page
      location.reload();
    },
    async handleSubmit(comment, rating) {
      const isValidated = await this.validate();
      var id = this.$route.params.hostelId;
      if (isValidated) {
        var jwtToken = this.$store.getters.jwtToken;
        console.log("jwtToken", jwtToken);
        HostelRequest.postHostelComment(id, comment, rating, jwtToken)
          .then(() => {
            this.successDialog = true;
            console.log(this.successDialog);
          })
          .catch((error) => {
            var errorStatus = error.response.status;

            if (errorStatus == 403) {
              // user is not logged in
              this.notLoggedInSnackbar = true;
              setTimeout(() => (this.failureSnackbar = false), 5000);
              return;
            }

            // other errors
            this.failureSnackbar = true;
            setTimeout(() => (this.failureSnackbar = false), 5000);
          });
      }
    },
  },
};
</script>

<style scoped>
.comment-form {
  text-align: left;
  padding: 0px 1rem;
}

label {
  display: inline-block;
  margin: 10px 0px;
  font-weight: bold;
}

.comment {
  width: 100%;
  max-width: 100%;
  background: transparent;
  display: block;
  font-size: 120%;
  padding: 15px 0px 0px 0px;
  border: none;
  color: #555;
  outline: none;
}

.comment :focus {
  border-color: #555;
}

.rating-display {
  display: flex;
  column-gap: 2rem;
}

.rating {
  max-width: 300px;
}
</style>
