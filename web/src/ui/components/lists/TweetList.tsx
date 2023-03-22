import styled from "styled-components";
import {Tweet} from "@/ui/components/lists/Tweet";

export const TweetList = ({posts}) => {
    return (
        <PostList>
            {posts.length > 0 && (
                posts.map((post) => (
                    <Tweet key={post.id} post={post} />
                ))
            )}
        </PostList>
    )
}

const PostList = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 4vh;
  justify-content: flex-start;
`;
