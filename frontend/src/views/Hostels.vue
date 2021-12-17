<template>
  <div class="hostels">
    <SearchBar @handle-search="handleSearch" searchHint="Search for hostels" />
    <div v-for="hostel in hostelList" :key="hostel.id" class="hostel-list">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <ItemCard type="hostel" :item="hostel" />
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
import ItemCard from "../components/commons/ItemCard";
import SearchBar from "../components/util/SearchBar";

export default {
  name: "Hostels",
  components: {
    ItemCard,
    SearchBar,
  },

  data() {
    return {
      hostelList: [],
      currentPage: 1,
      pageSize: 1,
      totalPages: 0,
      currentKeyword: "",
      hasBeenSearched: false,
    };
  },
  methods: {
    async getHostelList(pageNum, pageSize) {
      await HostelRequest.getHostelList(pageNum, pageSize)
        .then(async (response) => {
          console.log(response.data.content);
          this.hostelList = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },

    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      if (this.hasBeenSearched) {
        await this.handleSearch(this.currentKeyword);
      } else {
        await this.getHostelList(pageNumber - 1, this.pageSize);
      }
    },

    goToViewMorePage(hostelId) {
      this.$router.push("/hostels/" + hostelId);
    },

    async handleSearch(keyword) {
      console.log("keyword: " + keyword);
      var page;
      if (this.currentKeyword != keyword) {
        // first search
        console.log("first search");
        page = 0;
      } else {
        page = this.currentPage - 1;
      }
      console.log("currentKeyword: " + this.currentKeyword);
      console.log("page: " + page);
      HostelRequest.findHostels(keyword, page, this.pageSize)
        .then((response) => {
          console.log(response.data);
          this.currentPage = page + 1;
          this.hostelList = response.data.content;
          this.totalPages = response.data.totalPages;
          this.currentKeyword = keyword;
          this.hasBeenSearched = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  async created() {
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
