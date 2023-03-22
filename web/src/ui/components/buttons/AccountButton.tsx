import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";
import {useRouter} from "next/navigation";

export const AccountButton = ({value}) => {
    const router = useRouter()

    return (
        <Tooltip>
            <SecondaryButton
                onClick={() => router.push(`/users/${value}`)}
                value={value}
            />
        </Tooltip>
    )
}

const Tooltip = styled.div`
  visibility: initial;
  position: relative;
  display: inline-block;
  margin: 10px 10px;

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
