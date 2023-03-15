import {UserScreen} from "@/ui/screens/user/UserScreen";
import {NextJsRouter} from "@/ui/services/router/NextJsRouter";

export default () => {
    const router = new NextJsRouter()
    let {name} = router.query

    return (
        <UserScreen user={name}/>
    )
}
