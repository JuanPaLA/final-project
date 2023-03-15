import Header from "@/ui/layout/Header";
import Footer from "@/ui/layout/Footer";

export default function Layout({children} : any) {
    return (
        <>
            <Header/>
                {children}
            <Footer/>
        </>
    )
}