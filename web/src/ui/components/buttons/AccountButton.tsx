import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";
import Link from "next/link";

export const AccountButton = ({value}) => {

    return (
        <Tooltip>
            <Link href={`/users/${value}`}>
                <SecondaryButton
                    onClick={ () => console.log("") }
                    value={value}
                />
            </Link>
            {/*<span*/}
            {/*    className={'tooltip-text'}*/}
            {/*    onClick={() => alert('coso') }*/}
            {/*>*/}
            {/*    Follow {value}</span>*/}
        </Tooltip>
    )
}

const Tooltip = styled.div`
  visibility: initial;
  position: relative;
  display: inline-block;

  .tooltip-text {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    border: 1px solid ${colors.gray};
    padding: 10px;
    position: absolute;
    z-index: 1;
    bottom: 100%;
    border-radius: 5px;
    left: 50%;
  }

  &:hover .tooltip-text {
    visibility: visible;
  }
`;



