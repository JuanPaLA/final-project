import {WallService} from "@/core/model/WallService";

export class Wall {
    constructor(private wallService: WallService) {}

    async exec(name: string, token: string) {
        return await this.wallService.getTimeline(name, token)
    }
}