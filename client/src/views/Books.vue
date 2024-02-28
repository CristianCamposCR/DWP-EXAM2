<template>
  <div>
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item href="#">Libros</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <h3 class="text-center mt-3">Libros</h3>

    <b-container fluid>
      <b-row class="d-flex justify-content-center">
        <b-col cols="12" sm="12" md="8">
          <div>
            <b-carousel
              id="carousel-1"
              v-model="slide"
              :interval="4000"
              controls
              indicators
              background="#ababab"
              img-width="1024"
              img-height="480"
              style="text-shadow: 1px 1px 2px #333"
              @sliding-start="onSlideStart"
              @sliding-end="onSlideEnd"
            >
              <!-- Text slides with image -->
              <b-carousel-slide
                caption="First slide"
                text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                img-src="https://picsum.photos/1024/480/?image=52"
              ></b-carousel-slide>

              <!-- Slides with custom text -->
              <b-carousel-slide
                img-src="https://picsum.photos/1024/480/?image=54"
              >
                <h1>Hello world!</h1>
              </b-carousel-slide>

              <!-- Slides with image only -->
              <b-carousel-slide
                img-src="https://picsum.photos/1024/480/?image=58"
              ></b-carousel-slide>

              <!-- Slides with img slot -->
              <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
              <b-carousel-slide>
                <template #img>
                  <img
                    class="d-block img-fluid w-100"
                    width="1024"
                    height="480"
                    src="https://picsum.photos/1024/480/?image=55"
                    alt="image slot"
                  />
                </template>
              </b-carousel-slide>

              <!-- Slide with blank fluid image to maintain slide aspect ratio -->
              <b-carousel-slide
                caption="Blank Image"
                img-blank
                img-alt="Blank image"
              >
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Suspendisse eros felis, tincidunt a tincidunt eget, convallis
                  vel est. Ut pellentesque ut lacus vel interdum.
                </p>
              </b-carousel-slide>
            </b-carousel>
          </div>
        </b-col>
      </b-row>
      <b-row class="mt-5">
        <b-col cols="12" sm="12" md="10">
          <b-row class="mt-3 text-center">
            <b-col cols="12" sm="12" md="4">
              <b-button @click="getBooksOrderByAuthorDesc"
                >Ordenar por autor</b-button
              >
            </b-col>
            <b-col cols="12" sm="12" md="4">
              <b-button @click="getBooksOrderByDateDesc"
                >Ordenar por fecha</b-button
              >
            </b-col>
            <b-col cols="12" sm="12" md="4">
              <b-button @click="getBooksHasCover"
                >Mostrar si tiene imagen</b-button
              >
            </b-col>
          </b-row>
          <b-row
            cols="12"
            class="align-items-stretch d-flex justify-content-center mt-5 ml-5"
            v-if="books.length != 0"
          >
            <TransitionGroup
              name="backDown"
              tag="div"
              class="d-flex flex-row flex-wrap"
            >
              <b-col
                v-for="book in books"
                :key="book.id"
                class="my-2"
                cols="12"
                sm="12"
                md="4"
              >
                <b-card
                  :title="book.name"
                  :img-src="book.cover"
                  img-alt="Image"
                  img-top
                  tag="article"
                  style="max-width: 20rem"
                  class="mb-2"
                >
                  <b-card-text>
                    <!-- <p>Libro: {{ book.name }}</p> -->
                    <p>Autor: {{ book.author }}</p>
                    <p>Fecha de publicación: {{ book.atPublish }}</p>
                    <p>Estatus: {{ book.status ? "Activo" : "Inactivo" }}</p>
                  </b-card-text>
                </b-card>
              </b-col>
            </TransitionGroup>
          </b-row>
        </b-col>
        <b-col cols="12" sm="12" md="2">
          <b-row class="text-center">
            <b-col cols="12" sm="12" class="mb-2">
              <b-button v-b-modal.save
                ><b-icon icon="plus" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
            <b-col cols="12" sm="12" class="mb-2">
              <b-button
                ><b-icon icon="pencil" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
            <b-col cols="12" sm="12" class="mb-2">
              <b-button
                ><b-icon icon="trash-fill" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
    </b-container>

    <!-- modal -->
    <b-modal id="save" title="Registrar libro" size="lg" hide-footer>
      <b-row>
        <b-col cols="12" sm="12" md="6">
          <b-form>
            <b-form-group>
              <b-form-input
                id="input-1"
                type="text"
                placeholder="Ingresa el nombre del libro"
                v-model="book.name"
                required
              />
            </b-form-group>
          </b-form>
        </b-col>
        <b-col cols="12" sm="12" md="6">
          <b-form>
            <b-form-group>
              <b-form-input
                id="input-1"
                type="text"
                placeholder="Ingresa el nombre del autor"
                v-model="book.author"
                required
              />
            </b-form-group>
          </b-form>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="12" md="6">
          <b-form>
            <b-form-group>
              <b-form-input
                id="input-1"
                type="text"
                placeholder="Ingresa la fecha de publicación"
                v-model="book.atPublish"
                required
              />
            </b-form-group>
          </b-form>
        </b-col>
        <b-col cols="12" sm="12" md="6">
          <b-form>
            <b-form-group>
              <b-form-input
                id="input-1"
                type="text"
                placeholder="Ingresa el link de la imagen"
                v-model="book.cover"
                required
              />
            </b-form-group>
          </b-form>
        </b-col>
      </b-row>
      <div class="col-12 mt-3 px-0 text-right">
        <b-button variant="success" @click="saveBook"> Guardar </b-button>
      </div>
    </b-modal>
  </div>
</template>
<script>
import Vue from "vue";
import Axios from "axios";

export default Vue.extend({
  data() {
    return {
      slide: 0,
      sliding: null,
      books: [],
      pageable: {
        currentPage: 1,
        sort: "id",
        direction: "asc",
        page: 0,
        size: 4,
      },
      book: {
        author: "",
        name: "",
        atPublish: "",
        cover: "",
      },
    };
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    async getBooks() {
      try {
        this.isLoading = true;
        const response = await Axios.get(
          `http://localhost:8080/api/books/?sort=id,desc`
        );
        this.totalRows = response.data.totalElements;
        this.books = response.data.content;
      } catch (error) {
        throw error;
      } finally {
        this.isLoading = false;
      }
    },
    async saveBook() {
      try {
        const response = await Axios.post(
          "http://localhost:8080/api/books/",
          this.book
        );
        console.log(response);
        if (response) {
          this.$bvModal.hide("save");
          this.getBooks();
        }
      } catch (error) {
        throw error;
      }
    },

    async getBooksOrderByDateDesc() {
      try {
        this.isLoading = true;
        const response = await Axios.get(
          `http://localhost:8080/api/books/order-by-publish-date/`
        );
        this.totalRows = response.data.totalElements;
        this.books = response.data.content;
      } catch (error) {
        throw error;
      } finally {
        this.isLoading = false;
      }
    },
    async getBooksOrderByAuthorDesc() {
      try {
        this.isLoading = true;
        const response = await Axios.get(
          `http://localhost:8080/api/books/order-by-author/`
        );
        this.totalRows = response.data.totalElements;
        this.books = response.data.content;
      } catch (error) {
        throw error;
      } finally {
        this.isLoading = false;
      }
    },
    async getBooksHasCover() {
      try {
        this.isLoading = true;
        const response = await Axios.get(
          `http://localhost:8080/api/books/has-cover/`
        );
        this.totalRows = response.data.totalElements;
        this.books = response.data.content;
      } catch (error) {
        throw error;
      } finally {
        this.isLoading = false;
      }
    },
  },
  mounted() {
    this.getBooks();
  },
});
</script>
