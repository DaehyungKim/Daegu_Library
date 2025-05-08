import Layout from "../layouts"
import { Button } from "../components/Button";
import { useRecoilState } from "recoil";
import RecoilLoginState from '../atoms/loginState';





const LoginPage = () => {
    const [loginState, setLoginState ] = useRecoilState(RecoilLoginState);

    function LoginTest(){
        setLoginState({id : "user1"});
        alert("테스트 완료");
        }

    return (
        <Layout sideOn={false}>
        {
        loginState.id ?
        <> 테스트 완료 : {loginState.id}</>
        :
        <Button onClick = {LoginTest}>로그인</Button>
        }
        </Layout>
    )
}

export default LoginPage;