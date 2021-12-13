<template>
  <div class="hostels">
    <h1>Hostel List</h1>
    <SearchBar @handle-search="handleSearch" searchHint="Search for hostels" />
    <div v-for="hostel in hostelList" :key="hostel.id" class="hostel-list">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <Hostel :hostel="hostel" />
      </div>
    </div>
    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width">
              <v-pagination
                @input="updatePage"
                v-model="currentPage"
                class="my-4"
                :length="totalPages"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
              ></v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Hostel from "../components/hostels/Hostel.vue";
import SearchBar from "../components/util/SearchBar";

export default {
  name: "Hostels",
  components: {
    Hostel,
    SearchBar,
  },

  data() {
    return {
      hostelList: [],
      currentPage: 1,
      pageSize: 1,
      totalPages: 0,
    };
  },
  methods: {
    async getHostelList(pageNum, pageSize) {
      await HostelRequest.getHostelList(pageNum, pageSize)
        .then(async (response) => {
          this.hostelList = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },

    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      await this.getHostelList(pageNumber - 1, this.pageSize);
    },

    goToViewMorePage(hostelId) {
      this.$router.push("/hostels/" + hostelId);
    },

    handleSearch(keyword) {
      console.log("currentPage: " + this.currentPage);
      console.log("pageSize: " + this.pageSize);
      HostelRequest.findHostels(keyword, 0, this.pageSize)
        .then((response) => {
          console.log(response.data);
          this.hostelList = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  async mounted() {
    await this.getHostelList(this.currentPage - 1, this.pageSize);
  },

  emits: ["handle-search"],
};
</script>

<style scope>
.hostel-list {
  margin: auto;
}

#hostel-click:hover {
  cursor: pointer;
}

.search-bar {
  float: right;
}
</style>
