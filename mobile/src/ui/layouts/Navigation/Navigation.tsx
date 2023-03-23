import React, { FC } from 'react'
import { ReactNavigator } from '@/ui/services/navigator/ReactNavigator'
import { NavigationContainer } from '@react-navigation/native'
import { createNativeStackNavigator, NativeStackNavigationOptions } from '@react-navigation/native-stack'
import { rv } from '@/ui/services/responsive/ResponsiveUtils'
import { usePresenterFactory } from '@/ui/components/context/PresentersContext'
import { usePresenter } from '@/ui/services/presenters/usePresenter'
import { SignupScreen } from '@/ui/screens/Signup/SignupScreen'

const Stack = createNativeStackNavigator()

export const Navigation: FC<Props> = (props) => {
    const presenters = usePresenterFactory()
    const presenter = usePresenter(presenters.navigation)
    return (
        <NavigationContainer ref={props.navigator.ref}>
            <Stack.Navigator screenOptions={defaultScreenOptions}>
                <Stack.Group>
                    <Stack.Screen name="Signup" component={SignupScreen}/>
                </Stack.Group>
            </Stack.Navigator>
        </NavigationContainer>
    )
}

const defaultScreenOptions: NativeStackNavigationOptions = {
    headerStyle: {
        backgroundColor: '#000000',
    },
    headerTintColor: '#FFFFFF',
    headerTitleAlign: 'center',
    headerTitleStyle: {
        fontSize: rv(50),
    },
    contentStyle: {
        backgroundColor: '#F0F0F0',
    },
}

interface Props {
    navigator: ReactNavigator
}
