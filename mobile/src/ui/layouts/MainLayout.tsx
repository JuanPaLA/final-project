import { StatusBar } from 'react-native'
import { PresenterFactory } from '@/ui/PresenterFactory'
import { FC } from 'react'
import { PresentersProvider } from '@/ui/components/context/PresentersContext'
import { SafeAreaProvider } from 'react-native-safe-area-context'
import { Navigation } from '@/ui/layouts/Navigation/Navigation'
import { ReactNavigator } from '@/ui/services/navigator/ReactNavigator'

export const MainLayout: FC<Props> = (props) => (
    <>
        <StatusBar barStyle={'light-content'} backgroundColor="#000000" />
        <PresentersProvider factory={props.presenters}>
            <SafeAreaProvider>
                <Navigation navigator={props.navigator} />
            </SafeAreaProvider>
        </PresentersProvider>
    </>
)

interface Props {
    presenters: PresenterFactory,
    navigator: ReactNavigator,
}
