import Layout from "../layouts";
import FilterBookListComponent from "../components/books/FilterBookListComponent";
import SubHeader from "../layouts/SubHeader";
const BooksSearch = () => {
  return (
    <Layout>
        <SubHeader subTitle="통합검색" mainTitle="도서정보" />
        <FilterBookListComponent />
    </Layout>
  );
}

export default BooksSearch;