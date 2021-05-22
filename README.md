# until

A discord bot that kicks you out of voicechat after x minutes

## Setup

Create `data/settings/settings.json` containing:

Create `data/token.txt` containing:

```json
{
  "token": "Your Auth Token",
  "prefix": "!",
  "bot-name": "until",
  "extension-folders": [
    "src/discord/extensions"
  ]
}
```

## Usage

```shell
!talk-for <duration-in-minutes> @User1 @User2 ... @UserN
```
