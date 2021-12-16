<template>
<div>
  <v-row justify="center">
    <v-dialog
      v-model="successDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="text-h5">
          Success
        </v-card-title>
        <v-card-text>You have added a comment successfully!</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="white darken-1"
            text
            @click="refreshPage"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
  <v-snackbar top color="red" :value="failureSnackbar">An unknown error has occured, please try again!</v-snackbar>
  <v-card flat class="mx-16 mb-12">
    <div class="comment-form">
      <v-form>
        <div>
          <label class="rating-label"> Rating: </label>
          {{ rating }} / 5
          <div class="rating-display">
            <input
              class="rating"
              max="5"
              step="1"
              type="range"
              value="3"
              v-model="rating"
            />
          </div>
        </div>
        <ValidationObserver ref="addCommentObserver">
          <ValidationProvider name="Comment" rules="required" v-slot="{ errors }">
            <v-textarea
              label="Comment"
              v-model="comment"
              class="comment"
              placeholder="Join the discussion..."
              type="text"
              :min-height="30"
              :max-height="350"
              auto-grow
              :error-messages="errors"
            />
          </ValidationProvider>
      
        <div class="submit">
          <button @click="handleSubmit(comment, rating)">Submit</button>
        </div>
        </ValidationObserver>
      </v-form>
    </div>
  </v-card>
</div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import { ValidationProvider, extend, ValidationObserver, setInteractionMode } from 'vee-validate';
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
      successDialog:false,
      failureSnackbar: false
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
      const isValidated= await this.validate();
      var id = this.$route.params.hostelId;
      if (isValidated) {
          var jwtToken = this.$store.getters.jwtToken;
          console.log("jwtToken", jwtToken);
          HostelRequest.postHostelComment(id, comment, rating, jwtToken).then(() => {
            this.successDialog = true;
            console.log(this.successDialog);
          }).catch((error) => {
            console.log(error);
            this.failureSnackbar = true;
            setTimeout(() => (this.failureSnackbar = false), 5000);
          })
      }
    },
  },
};
</script>

<style scoped>
.comment-form {
  max-width: 100%;
  /* background: rgba(214, 238, 245, 0.5); */
  text-align: left;
  border-radius: 30px;
  padding: 30px 40px;
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

.submit {
  text-align: center;
}

button {
  background: #428dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
}

button:hover {
  background: #569aff;
}

button:active {
  background: #2c80ff;
}
</style>
