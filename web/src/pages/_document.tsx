import { Head, Html, Main, NextScript } from 'next/document'
import {StyledMain} from "@/ui/layout/styles/Globals";

export default function Document() {
    return (
        <Html lang="en">
            <Head/>
                <body>
                    <StyledMain>
                        <Main/>
                        <NextScript/>
                    </StyledMain>
                </body>
        </Html>
    )
}
