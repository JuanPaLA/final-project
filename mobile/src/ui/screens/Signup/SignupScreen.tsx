import React from 'react'
import { Text, TouchableHighlight, View } from 'react-native'
import { usePresenterFactory } from '../../components/context/PresentersContext'
import { usePresenter } from '../../services/presenters/usePresenter'
import { styled } from '@/ui/services/styles/styled'
import { rv } from '@/ui/services/responsive/ResponsiveUtils'

export const SignupScreen = () => {
    const presenters = usePresenterFactory()
    const presenter = usePresenter(presenters.signup)
    return (
        <Form>
            <SignupButton onPress={() => presenter.signup()}><SignupText>Signup</SignupText></SignupButton>
        </Form>
    )
}
const Form = styled(View, {
    width: '100%',
    height: '100%',
    justifyContent: 'center',
    alignItems: 'center',
})


const SignupButton = styled(TouchableHighlight, {
    width: rv(1000),
    backgroundColor: 'black',
    height: rv(200),
    borderRadius: 5,
    justifyContent: 'center'
})

const SignupText = styled(Text, {
    textAlign: 'center',
    color: 'white',
})
